package org.example.dao;

import org.example.entity.Account;

import javax.ejb.Stateless;

@Stateless
public class AccountDAO extends BaseDAO<Account> {
    public AccountDAO() {
        super(Account.class);
    }
}
