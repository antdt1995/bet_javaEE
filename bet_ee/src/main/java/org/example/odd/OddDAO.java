package org.example.odd;

import org.example.matchresult.MatchResult;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Stateless
public class OddDAO {
    @PersistenceContext(name = "betting")
    private EntityManager em;

    public Odd save(Odd odd) {
        em.persist(odd);
        return odd;
    }

    public List<Odd> getAll() {
        CriteriaBuilder cr = em.getCriteriaBuilder();
        CriteriaQuery<Odd> cq = cr.createQuery(Odd.class);
        Root<Odd> root = cq.from(Odd.class);
        cq.select(root);
        return em.createQuery(cq).getResultList();
    }

    public Optional<Odd> findById(Long id) {
        CriteriaBuilder cr = em.getCriteriaBuilder();
        CriteriaQuery<Odd> cq = cr.createQuery(Odd.class);
        Root<Odd> root = cq.from(Odd.class);
        cq.select(root);
        cq.where(cr.equal(root.get("id"), id));
        return em.createQuery(cq).getResultList().stream().findFirst();
    }

    public Odd findWinOdd(Long matchId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Odd> cq = cb.createQuery(Odd.class);

        Root<Odd> oddRoot = cq.from(Odd.class);
        Join<Odd, MatchResult> matchResultJoin = oddRoot.join("matchResult");

        Predicate condition = cb.and(
                cb.equal(matchResultJoin.get("match").get("id"), matchId),
                cb.equal(matchResultJoin.get("resultEnum"), oddRoot.get("oddType"))
        );

        cq.where(condition);

        List<Odd> result = em.createQuery(cq).getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

}
