package com.academiafitnessapi.mapper;

import com.academiafitnessapi.dto.ModalidadeRequestDTO;
import com.academiafitnessapi.dto.ModalidadeResponseDTO;
import com.academiafitnessapi.model.Modalidade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ModalidadeMapper {

    Modalidade toEntity(ModalidadeRequestDTO dto);

    ModalidadeResponseDTO toResponseDTO(Modalidade entity);
}
