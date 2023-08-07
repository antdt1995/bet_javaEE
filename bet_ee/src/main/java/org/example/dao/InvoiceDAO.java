package org.example.dao;

import org.example.entity.Invoice;

import javax.ejb.Stateless;

@Stateless
public class InvoiceDAO extends BaseDAO<Invoice> {
    public InvoiceDAO() {
        super(Invoice.class);
    }
}
