package org.example.invoice;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class InvoiceDAO {
    @PersistenceContext(name = "betting")
    private EntityManager em;
}
