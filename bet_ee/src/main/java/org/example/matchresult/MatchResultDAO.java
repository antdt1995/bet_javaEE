package org.example.matchresult;

import org.example.footballmatch.FootballMatch;
import org.example.matchresult.MatchResult;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
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
    public Optional<MatchResult> findByMatchId(Long matchId){
        CriteriaBuilder cr=em.getCriteriaBuilder();
        CriteriaQuery<MatchResult> cq = cr.createQuery(MatchResult.class);
        Root<MatchResult> root = cq.from(MatchResult.class);
        Join<MatchResult, FootballMatch> join = root.join("match");
        cq.select(root);
        cq.where(cr.equal(join.get("id"), matchId));
        return em.createQuery(cq).getResultList().stream().findFirst();
    }
}

