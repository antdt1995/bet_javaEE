package org.example.mapper;

import org.example.entity.FootballTeam;
import org.example.model.FootballTeamDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface FootballTeamMapper {
    FootballTeamDTO toDto(FootballTeam footballTeam);

    List<FootballTeamDTO> toDtoList(List<FootballTeam> footballTeamList);
}
