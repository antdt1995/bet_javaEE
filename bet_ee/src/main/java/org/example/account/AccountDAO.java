package org.example.account;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
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
    public List<Account> findByMatchId(Long matchId) {
        return em.createQuery("SELECT a " +
                        "FROM Account a " +
                        "JOIN a.invoices i " +
                        "JOIN i.invoiceDetails id " +
                        "JOIN id.odd o " +
                        "JOIN o.matchResult mr " +
                        "WHERE mr.match.id = :matchId", Account.class)
                .setParameter("matchId", matchId)
                .getResultList();
    }

    public Account save(Account account) {
        em.persist(account);
        return account;
    }
}
