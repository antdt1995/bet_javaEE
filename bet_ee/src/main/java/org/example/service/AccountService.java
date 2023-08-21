package org.example.service;

import org.example.dao.AccountDAO;
import org.example.entity.Account;
import org.example.exception.EntityNotFoundException;
import org.example.exception.ErrorMessage;
import org.example.mapper.AccountMapper;
import org.example.model.AccountDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AccountService {
    @Inject
    private AccountDAO accountDAO;
    @Inject
   private AccountMapper accountMapper;

    public AccountDTO findByUsername(String username) throws EntityNotFoundException {
        Account account = accountDAO.findByUsername(username).orElseThrow(()-> new EntityNotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND_MSG_KEY,ErrorMessage.ACCOUNT_NOT_FOUND_MSG));
        return accountMapper.toDTO(account);
    }


}
