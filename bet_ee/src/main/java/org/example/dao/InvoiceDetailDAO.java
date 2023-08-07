package org.example.dao;

import org.example.entity.InvoiceDetail;

import javax.ejb.Stateless;

@Stateless
public class InvoiceDetailDAO extends BaseDAO<InvoiceDetail> {
    public InvoiceDetailDAO() {
        super(InvoiceDetail.class);
    }
}
