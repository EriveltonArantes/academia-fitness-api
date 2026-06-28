package com.academiafitnessapi.service;

import com.academiafitnessapi.dto.ModalidadeRequestDTO;
import com.academiafitnessapi.dto.ModalidadeResponseDTO;
import com.academiafitnessapi.exception.ResourceNotFoundException;
import com.academiafitnessapi.mapper.ModalidadeMapper;
import com.academiafitnessapi.model.Modalidade;
import com.academiafitnessapi.repository.ModalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ModalidadeService {

    @Autowired
    private ModalidadeRepository repository;

    @Autowired
    private ModalidadeMapper mapper;

    @Transactional(readOnly = true)
    public org.springframework.data.domain.Page<ModalidadeResponseDTO> listar(String nome, int page, int size) {
        org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size, org.springframework.data.domain.Sort.by("id").descending());
        if (nome != null && !nome.isBlank()) {
            return repository.findByNomeContainingIgnoreCase(nome, pageable)
                .map(mapper::toResponseDTO);
        }
        return repository.findAll(pageable).map(mapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public ModalidadeResponseDTO buscar(Long id) {
        Modalidade entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Modalidade não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public ModalidadeResponseDTO criar(ModalidadeRequestDTO dto) {
        Modalidade entity = mapper.toEntity(dto);
        Modalidade salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public ModalidadeResponseDTO atualizar(Long id, ModalidadeRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Modalidade não encontrado com id: " + id);
        }
        Modalidade entity = mapper.toEntity(dto);
        entity.setId(id);
        Modalidade salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Modalidade não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
