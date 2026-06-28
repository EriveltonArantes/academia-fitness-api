package com.academiafitnessapi.mapper;

import com.academiafitnessapi.dto.PlanoRequestDTO;
import com.academiafitnessapi.dto.PlanoResponseDTO;
import com.academiafitnessapi.model.Plano;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlanoMapper {

    Plano toEntity(PlanoRequestDTO dto);

    PlanoResponseDTO toResponseDTO(Plano entity);
}
