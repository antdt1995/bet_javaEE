package org.example.invoicedetail;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class InvoiceDetailDAO {
    @PersistenceContext(name = "betting")
    private EntityManager em;
}
