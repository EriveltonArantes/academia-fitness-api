package com.academiafitnessapi.service;

import com.academiafitnessapi.dto.PlanoRequestDTO;
import com.academiafitnessapi.dto.PlanoResponseDTO;
import com.academiafitnessapi.exception.ResourceNotFoundException;
import com.academiafitnessapi.mapper.PlanoMapper;
import com.academiafitnessapi.model.Plano;
import com.academiafitnessapi.repository.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlanoService {

    @Autowired
    private PlanoRepository repository;

    @Autowired
    private PlanoMapper mapper;

    public List<PlanoResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    public PlanoResponseDTO buscar(Long id) {
        Plano entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Plano não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public PlanoResponseDTO criar(PlanoRequestDTO dto) {
        Plano entity = mapper.toEntity(dto);
        Plano salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public PlanoResponseDTO atualizar(Long id, PlanoRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Plano não encontrado com id: " + id);
        }
        Plano entity = mapper.toEntity(dto);
        entity.setId(id);
        Plano salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Plano não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
