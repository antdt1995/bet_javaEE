package org.example.service;

import org.example.dao.FootballTeamDAO;
import org.example.entity.FootballTeam;
import org.example.mapper.FootballTeamMapper;
import org.example.model.FootballTeamDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class FootballTeamService {
    @Inject
    private FootballTeamDAO footballTeamDAO;

    @Inject
    private FootballTeamMapper footballTeamMapper;

    public FootballTeamDTO getById(Long id){
        FootballTeam footballTeam =
                footballTeamDAO.findById(id)
                        .orElseThrow(()-> new RuntimeException("Team not found"));
        return footballTeamMapper.toDto(footballTeam);
    }
}
