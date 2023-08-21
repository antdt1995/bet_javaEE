package org.example.footballteam;

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
