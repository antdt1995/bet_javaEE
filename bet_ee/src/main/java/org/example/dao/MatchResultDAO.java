package org.example.dao;

import org.example.entity.MatchResult;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Stateless
public class MatchResultDAO {
    @PersistenceContext(name = "betting")
    private EntityManager em;

    public MatchResult save(MatchResult matchResult) {
        em.persist(matchResult);
        return matchResult;
    }
    public Optional<MatchResult> findById(Long id){
        CriteriaBuilder cr = em.getCriteriaBuilder();
        CriteriaQuery<MatchResult> cq = cr.createQuery(MatchResult.class);
        Root<MatchResult> root = cq.from(MatchResult.class);
        cq.select(root);
        cq.where(cr.equal(root.get("id"), id));
        return em.createQuery(cq).getResultList().stream().findFirst();
    }
}

