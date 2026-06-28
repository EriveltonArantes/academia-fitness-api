package com.academiafitnessapi.mapper;

import com.academiafitnessapi.dto.InstrutorRequestDTO;
import com.academiafitnessapi.dto.InstrutorResponseDTO;
import com.academiafitnessapi.model.Instrutor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InstrutorMapper {

    Instrutor toEntity(InstrutorRequestDTO dto);

    InstrutorResponseDTO toResponseDTO(Instrutor entity);
}
