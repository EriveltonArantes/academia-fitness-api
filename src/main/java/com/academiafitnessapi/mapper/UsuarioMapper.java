package com.academiafitnessapi.mapper;

import com.academiafitnessapi.dto.UsuarioRequestDTO;
import com.academiafitnessapi.dto.UsuarioResponseDTO;
import com.academiafitnessapi.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioRequestDTO dto);

    UsuarioResponseDTO toResponseDTO(Usuario entity);
}
