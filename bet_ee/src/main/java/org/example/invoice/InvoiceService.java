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
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
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
    @Inject
    private ContainerRequestContext requestContext;

    public String getAccount(){
        String authHeader=requestContext.getHeaderString("Authorization");
      return authenticationService.getUsernameFromToken(authHeader);
    }
    public InvoiceDTO create (List<InvoiceDetailDTO> invoiceDTO) throws EntityNotFoundException, InputValidationException {
        Invoice invoice= new Invoice();
        Account account=accountDAO.findByUsername(getAccount()).orElseThrow(() -> new EntityNotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND_MSG_KEY, ErrorMessage.ACCOUNT_NOT_FOUND_MSG));
        Double totalBet = 0.0;

        List<InvoiceDetail> invoiceDetailList = new ArrayList<>();
        for(InvoiceDetailDTO id : invoiceDTO) {
            Odd odd = oddDAO.findById(id.getOddId()).orElseThrow(() -> new EntityNotFoundException(ErrorMessage.ODD_NOT_FOUND_MSG_KEY, ErrorMessage.ODD_NOT_FOUND_MSG));
            InvoiceDetail invoiceDetail = InvoiceDetail.builder()
                    .betAmount(id.getBetAmount())
                    .odd(odd)
                    .paymentStatus(Boolean.FALSE)
                    .build();
           invoiceDetailList.add(invoiceDetail);
           totalBet += id.getBetAmount();
        }
        invoice.setTotalBet(totalBet);
        invoice.setInvoiceDetails(invoiceDetailList);
        invoiceDAO.save(invoice);

        if (invoice.getTotalBet() > account.getTotalBalance()) {
            throw new InputValidationException(ErrorMessage.AMOUNT_OVER_BALANCE_MSG_KEY, ErrorMessage.AMOUNT_OVER_BALANCE_MSG);
        }
        Double balance = account.getTotalBalance() - invoice.getTotalBet();
        account.setTotalBalance(balance);
        accountDAO.save(account);

        return invoiceMapper.toDto(invoice);
    }
}
