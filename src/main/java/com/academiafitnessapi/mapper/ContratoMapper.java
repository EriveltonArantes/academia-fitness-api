package com.academiafitnessapi.mapper;

import com.academiafitnessapi.dto.ContratoRequestDTO;
import com.academiafitnessapi.dto.ContratoResponseDTO;
import com.academiafitnessapi.model.Contrato;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContratoMapper {

    @Mapping(target = "aluno", ignore = true)
    @Mapping(target = "plano", ignore = true)
    Contrato toEntity(ContratoRequestDTO dto);

    @Mapping(target = "alunoId", source = "aluno.id")
    @Mapping(target = "planoId", source = "plano.id")
    ContratoResponseDTO toResponseDTO(Contrato entity);
}
