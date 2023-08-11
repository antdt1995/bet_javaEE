package org.example.dao;

import org.example.entity.FootballTeam;

import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class FootballTeamDAO {
    @PersistenceContext(name = "betting")
    private EntityManager em;

    public Optional<FootballTeam> findById(Long id){
        List<FootballTeam> footballTeams =
                em.createQuery("SELECT ft FROM FootballTeam ft WHERE ft.id = :id", FootballTeam.class)
                .setParameter("id", id)
                .getResultList();
        return footballTeams.isEmpty() ? Optional.empty() : Optional.of(footballTeams.get(0));
    }
}

