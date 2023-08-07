package org.example.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;
@Mapper(componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface baseMapper<E , D> {
    D toDTO(E entity);
    List<D> toDTOList(List<E> entityList);
}
