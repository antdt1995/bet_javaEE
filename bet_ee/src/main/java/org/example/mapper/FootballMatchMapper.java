package org.example.mapper;

import org.example.entity.FootballMatch;
import org.example.model.FootballMatchDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface FootballMatchMapper {

    @Mapping(source = "homeTeam.name", target = "homeTeamName")
    @Mapping(source = "awayTeam.name", target = "awayTeamName")
    FootballMatchDTO toDTO(FootballMatch entity);
    List<FootballMatchDTO> toDTOList(List<FootballMatch> entityList);
}
