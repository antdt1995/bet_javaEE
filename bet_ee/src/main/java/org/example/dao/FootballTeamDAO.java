package org.example.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class FootballTeamDAO {
    @PersistenceContext(name = "betting")
    private EntityManager em;
}
