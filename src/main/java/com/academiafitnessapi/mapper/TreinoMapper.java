package com.academiafitnessapi.mapper;

import com.academiafitnessapi.dto.TreinoRequestDTO;
import com.academiafitnessapi.dto.TreinoResponseDTO;
import com.academiafitnessapi.model.Treino;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TreinoMapper {

    @Mapping(target = "instrutor", ignore = true)
    Treino toEntity(TreinoRequestDTO dto);

    @Mapping(target = "instrutorId", source = "instrutor.id")
    TreinoResponseDTO toResponseDTO(Treino entity);
}
