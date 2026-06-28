package com.academiafitnessapi.mapper;

import com.academiafitnessapi.dto.InstrutorRequestDTO;
import com.academiafitnessapi.dto.InstrutorResponseDTO;
import com.academiafitnessapi.model.Instrutor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InstrutorMapper {

    @Mapping(target = "alunosAtivos", ignore = true)
    @Mapping(target = "contratosVencendo", ignore = true)
    @Mapping(target = "planosAssociados", ignore = true)
    Instrutor toEntity(InstrutorRequestDTO dto);

    @Mapping(target = "alunosAtivosId", source = "alunosAtivos.id")
    @Mapping(target = "contratosVencendoId", source = "contratosVencendo.id")
    @Mapping(target = "planosAssociadosId", source = "planosAssociados.id")
    InstrutorResponseDTO toResponseDTO(Instrutor entity);
}
