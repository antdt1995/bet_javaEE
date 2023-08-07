package org.example.dao;

import org.example.entity.Odd;

import javax.ejb.Stateless;

@Stateless
public class OddDAO extends BaseDAO<Odd> {
    public OddDAO() {
        super(Odd.class);
    }
}
