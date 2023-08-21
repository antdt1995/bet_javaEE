package org.example.footballteam;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface FootballTeamMapper {
    FootballTeamDTO toDto(FootballTeam footballTeam);

    List<FootballTeamDTO> toDtoList(List<FootballTeam> footballTeamList);
}
