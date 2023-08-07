package org.example.dao;

import org.example.entity.FootballTeam;

import javax.ejb.Stateless;

@Stateless
public class FootballTeamDAO extends BaseDAO<FootballTeam> {

    public FootballTeamDAO() {
        super(FootballTeam.class);
    }
}
