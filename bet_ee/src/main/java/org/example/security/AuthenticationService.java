package org.example.security;

import org.example.exception.EntityNotFoundException;
import org.example.model.AccountDTO;
import org.example.service.AccountService;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AuthenticationService {
    @Inject
    private AccountService accountService;

    private Boolean checkAuthentication(String username, String password) throws EntityNotFoundException {
        AccountDTO accountDTO = accountService.findByUsername(username);
        return BCrypt.checkpw(password, accountDTO.getPassword());
    }
}
