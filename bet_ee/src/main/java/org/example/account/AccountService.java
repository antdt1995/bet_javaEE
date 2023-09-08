package org.example.account;

import org.example.enumclass.RoleEnum;
import org.example.exception.EntityNotFoundException;
import org.example.exception.ErrorMessage;
import org.example.invoice.Invoice;
import org.example.odd.OddDTO;
import org.example.odd.OddService;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Stateless
public class AccountService {
    public static final double TOTAL_BALANCE = 0.0;
    @Inject
    private AccountDAO accountDAO;
    @Inject
    private AccountMapper accountMapper;
    @Inject
    private OddService oddService;

    public Account findByUsername(String username) throws EntityNotFoundException {
        return accountDAO.findByUsername(username).orElseThrow(() -> new EntityNotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND_MSG_KEY, ErrorMessage.ACCOUNT_NOT_FOUND_MSG));
    }

    public AccountDTO create(AccountDTO accountDTO) {
        Account account = Account.builder()
                .active(true)
                .roleEnum(RoleEnum.ROLE_USER)
                .totalBalance(TOTAL_BALANCE)
                .username(accountDTO.getUsername())
                .password(BCrypt.hashpw(accountDTO.getPassword(), BCrypt.gensalt()))
                .build();
        return accountMapper.toDTO(accountDAO.save(account));
    }

    public void handlePayment(Long matchId) {
        List<Account> accountList = accountDAO.findByMatchId(matchId);
        OddDTO oddDTO=oddService.findWinOdd(matchId);
        Long oddId = oddDTO.getId();
        AtomicReference<Double> betWin = new AtomicReference<>(0.0);
        for (Account account : accountList) {
           for(Invoice invoice : account.getInvoices()){
               invoice.getInvoiceDetails().forEach(invoiceDetail -> {
                   if(Objects.equals(invoiceDetail.getOdd().getId(), oddId)){
                      betWin.set(invoiceDetail.getBetAmount() * oddDTO.getOddRate());
                   }
                   invoiceDetail.setPaymentStatus(true);
               });
           }
           account.setTotalBalance(account.getTotalBalance()+betWin.get());
           accountDAO.save(account);
        }
    }


}
