package org.example.invoice;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class InvoiceDAO {
    @PersistenceContext(name = "betting")
    private EntityManager em;
    public Invoice save(Invoice invoice){
        em.persist(invoice);
        return invoice;
    }
    public List<Invoice> getAll(){
        CriteriaBuilder cr = em.getCriteriaBuilder();
        CriteriaQuery<Invoice> cq = cr.createQuery(Invoice.class);
        Root<Invoice> root = cq.from(Invoice.class);
        cq.select(root);
        return em.createQuery(cq).getResultList();
    }
}
