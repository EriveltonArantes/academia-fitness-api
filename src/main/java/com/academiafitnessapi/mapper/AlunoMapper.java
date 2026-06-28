package com.academiafitnessapi.mapper;

import com.academiafitnessapi.dto.AlunoRequestDTO;
import com.academiafitnessapi.dto.AlunoResponseDTO;
import com.academiafitnessapi.model.Aluno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AlunoMapper {

    Aluno toEntity(AlunoRequestDTO dto);

    AlunoResponseDTO toResponseDTO(Aluno entity);
}
