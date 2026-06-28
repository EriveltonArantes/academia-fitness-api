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

@Service
@Transactional
public class PlanoService {

    @Autowired
    private PlanoRepository repository;

    @Autowired
    private PlanoMapper mapper;

    @Transactional(readOnly = true)
    public org.springframework.data.domain.Page<PlanoResponseDTO> listar(String nome, int page, int size) {
        org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size, org.springframework.data.domain.Sort.by("id").descending());
        if (nome != null && !nome.isBlank()) {
            return repository.findByNomeContainingIgnoreCase(nome, pageable)
                .map(mapper::toResponseDTO);
        }
        return repository.findAll(pageable).map(mapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
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
