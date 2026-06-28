package com.academiafitnessapi.mapper;

import com.academiafitnessapi.dto.UsuarioSistemaRequestDTO;
import com.academiafitnessapi.dto.UsuarioSistemaResponseDTO;
import com.academiafitnessapi.model.UsuarioSistema;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioSistemaMapper {

    UsuarioSistema toEntity(UsuarioSistemaRequestDTO dto);

    UsuarioSistemaResponseDTO toResponseDTO(UsuarioSistema entity);
}
