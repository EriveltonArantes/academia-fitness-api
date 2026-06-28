package com.academiafitnessapi.mapper;

import com.academiafitnessapi.dto.MatriculaRequestDTO;
import com.academiafitnessapi.dto.MatriculaResponseDTO;
import com.academiafitnessapi.model.Matricula;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MatriculaMapper {

    @Mapping(target = "aluno", ignore = true)
    @Mapping(target = "modalidade", ignore = true)
    Matricula toEntity(MatriculaRequestDTO dto);

    @Mapping(target = "alunoId", source = "aluno.id")
    @Mapping(target = "modalidadeId", source = "modalidade.id")
    MatriculaResponseDTO toResponseDTO(Matricula entity);
}
