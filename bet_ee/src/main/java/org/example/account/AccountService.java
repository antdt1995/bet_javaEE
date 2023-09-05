package org.example.account;

import org.example.enumclass.RoleEnum;
import org.example.exception.EntityNotFoundException;
import org.example.exception.ErrorMessage;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AccountService {
    @Inject
    private AccountDAO accountDAO;
    @Inject
   private AccountMapper accountMapper;

    public Account findByUsername(String username) throws EntityNotFoundException {
        return accountDAO.findByUsername(username).orElseThrow(()-> new EntityNotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND_MSG_KEY,ErrorMessage.ACCOUNT_NOT_FOUND_MSG));
    }

    public AccountDTO create(AccountDTO accountDTO) {
        Account account = Account.builder()
                .active(true)
                .roleEnum(RoleEnum.ROLE_USER)
                .username(accountDTO.getUsername())
                .password(BCrypt.hashpw(accountDTO.getPassword(), BCrypt.gensalt()))
                .build();
        return accountMapper.toDTO(accountDAO.save(account));
    }


}
