package org.example.dao;

import org.example.entity.Account;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Stateless
public class AccountDAO {
    @PersistenceContext(name = "betting")
    private EntityManager em;

    public Optional<Account> findByUsername(String username) {
        CriteriaBuilder cr = em.getCriteriaBuilder();
        CriteriaQuery<Account> cq = cr.createQuery(Account.class);
        Root<Account> root = cq.from(Account.class);
        cq.select(root);
        cq.where(cr.equal(root.get("username"), username));
        return em.createQuery(cq).getResultList().stream().findFirst();
    }
}
