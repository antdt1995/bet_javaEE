package org.example.mapper;

import org.example.entity.Odd;
import org.example.model.OddDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface OddMapper {

    @Mapping(source = "matchResult.match.homeTeam.name", target = "homeTeamName")
    OddDTO toDTO(Odd odd);

    List<OddDTO> toDTOS(List<Odd>odds);
}
