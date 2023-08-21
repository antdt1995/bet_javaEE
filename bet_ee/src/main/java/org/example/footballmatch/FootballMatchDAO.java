package org.example.footballmatch;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Stateless
public class FootballMatchDAO {

    @PersistenceContext(name = "betting")
    private EntityManager em;

    public List<FootballMatch> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<FootballMatch> cq = cb.createQuery(FootballMatch.class);
        Root<FootballMatch> root = cq.from(FootballMatch.class);
        cq.select(root);
        return em.createQuery(cq).getResultList();
    }
    public Optional<FootballMatch> findById(Long id){
        FootballMatch footballMatch=em.find(FootballMatch.class, id);
        return footballMatch==null?Optional.empty():Optional.of(footballMatch);
    }
    public FootballMatch save(FootballMatch footballMatch) {
        em.persist(footballMatch);
        return footballMatch;
    }

    public FootballMatch update (FootballMatch footballMatch) {

        return em.merge(footballMatch);
    }

    public void delete(Long id) {
        FootballMatch footballMatch = em.find(FootballMatch.class, id);
        em.remove(footballMatch);
    }
}
