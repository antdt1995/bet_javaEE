package org.example.invoice;

import org.example.account.Account;
import org.example.account.AccountDAO;
import org.example.exception.EntityNotFoundException;
import org.example.exception.ErrorMessage;
import org.example.exception.InputValidationException;
import org.example.invoicedetail.InvoiceDetail;
import org.example.invoicedetail.InvoiceDetailDTO;
import org.example.odd.Odd;
import org.example.odd.OddDAO;
import org.example.security.AuthenticationService;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Provider
public class InvoiceService {
    private static final Validator validator = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory()
            .getValidator();
    @Inject
    private InvoiceDAO invoiceDAO;
    @Inject
    private InvoiceMapper invoiceMapper;
    @Inject
    private OddDAO oddDAO;
    @Inject
    private AuthenticationService authenticationService;
    @Inject
    private AccountDAO accountDAO;

    @Context
    private HttpHeaders httpHeaders;

    public String getCurrentUsername() {
        String authorizationHeader = httpHeaders.getHeaderString("Authorization");
        return authenticationService.getUsernameFromToken(authorizationHeader);
    }

    @Transactional
    public InvoiceDTO create(List<InvoiceDetailDTO> invoiceDTO) throws EntityNotFoundException, InputValidationException {
        Invoice invoice = new Invoice();
        Account account = accountDAO.findByUsername(getCurrentUsername()).orElseThrow(() -> new EntityNotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND_MSG_KEY, ErrorMessage.ACCOUNT_NOT_FOUND_MSG));

        Double totalBet = 0.0;

        List<InvoiceDetail> invoiceDetailList = new ArrayList<>();
        for (InvoiceDetailDTO id : invoiceDTO) {

            totalBet = getTotalBet(invoice, totalBet, invoiceDetailList, id);
        }
        invoice.setAccount(account);
        invoice.setTotalBet(totalBet);
        invoice.setInvoiceDetails(invoiceDetailList);
        invoiceDAO.save(invoice);

        calcAccountBalance(invoice, account);

        return invoiceMapper.toDto(invoice);
    }

    private void calcAccountBalance(Invoice invoice, Account account) throws InputValidationException {
        if (invoice.getTotalBet() > account.getTotalBalance()) {
            throw new InputValidationException(ErrorMessage.AMOUNT_OVER_BALANCE_MSG_KEY, ErrorMessage.AMOUNT_OVER_BALANCE_MSG);
        }
        Double balance = account.getTotalBalance() - invoice.getTotalBet();
        account.setTotalBalance(balance);
        accountDAO.save(account);
    }

    private Double getTotalBet(Invoice invoice, Double totalBet, List<InvoiceDetail> invoiceDetailList, InvoiceDetailDTO id) throws InputValidationException, EntityNotFoundException {
        if (id.getBetAmount() < 0) {
            throw new InputValidationException(ErrorMessage.INVALID_BET_AMOUNT_MSG_KEY, ErrorMessage.INVALID_BET_AMOUNT_MSG);
        }
        Odd odd = oddDAO.findById(id.getOddId()).orElseThrow(() -> new EntityNotFoundException(ErrorMessage.ODD_NOT_FOUND_MSG_KEY, ErrorMessage.ODD_NOT_FOUND_MSG));

        if (odd.getMatchResult().getMatch().getStartDate().isBefore(LocalDateTime.now())) {
            throw new InputValidationException(ErrorMessage.ODD_EXPIRED_MSG_KEY, ErrorMessage.ODD_EXPIRED_MSG);
        }
        InvoiceDetail invoiceDetail = InvoiceDetail.builder()
                .betAmount(id.getBetAmount())
                .odd(odd)
                .paymentStatus(Boolean.FALSE)
                .invoice(invoice)
                .build();
        invoiceDetailList.add(invoiceDetail);
        totalBet += id.getBetAmount();
        return totalBet;
    }
}
