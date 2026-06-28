package com.academiafitnessapi.service;

import com.academiafitnessapi.dto.InstrutorRequestDTO;
import com.academiafitnessapi.dto.InstrutorResponseDTO;
import com.academiafitnessapi.exception.ResourceNotFoundException;
import com.academiafitnessapi.exception.BusinessException;
import com.academiafitnessapi.mapper.InstrutorMapper;
import com.academiafitnessapi.model.Instrutor;
import com.academiafitnessapi.repository.InstrutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InstrutorService {

    @Autowired
    private InstrutorRepository repository;

    @Autowired
    private InstrutorMapper mapper;

    @Transactional(readOnly = true)
    public org.springframework.data.domain.Page<InstrutorResponseDTO> listar(String nome, int page, int size) {
        org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size, org.springframework.data.domain.Sort.by("id").descending());
        if (nome != null && !nome.isBlank()) {
            return repository.findByNomeContainingIgnoreCase(nome, pageable)
                .map(mapper::toResponseDTO);
        }
        return repository.findAll(pageable).map(mapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public InstrutorResponseDTO buscar(Long id) {
        Instrutor entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Instrutor não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public InstrutorResponseDTO criar(InstrutorRequestDTO dto) {
        if (repository.existsByEmail(dto.getEmail())) {
            throw new BusinessException("Email já cadastrado: " + dto.getEmail());
        }
        Instrutor entity = mapper.toEntity(dto);
        Instrutor salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public InstrutorResponseDTO atualizar(Long id, InstrutorRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Instrutor não encontrado com id: " + id);
        }
        if (repository.existsByEmailAndIdNot(dto.getEmail(), id)) {
            throw new BusinessException("Email já cadastrado em outro registro: " + dto.getEmail());
        }
        Instrutor entity = mapper.toEntity(dto);
        entity.setId(id);
        Instrutor salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Instrutor não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
