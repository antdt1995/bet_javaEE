package org.example.dao;

import org.example.entity.MatchResult;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MatchResultDAO {
    @PersistenceContext(name = "betting")
    private EntityManager em;

    public MatchResult save(MatchResult matchResult) {
        em.persist(matchResult);
        return matchResult;
    }
}

