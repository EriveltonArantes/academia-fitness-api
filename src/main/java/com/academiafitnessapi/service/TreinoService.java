package com.academiafitnessapi.service;

import com.academiafitnessapi.dto.TreinoRequestDTO;
import com.academiafitnessapi.dto.TreinoResponseDTO;
import com.academiafitnessapi.exception.ResourceNotFoundException;
import com.academiafitnessapi.mapper.TreinoMapper;
import com.academiafitnessapi.model.Treino;
import com.academiafitnessapi.repository.TreinoRepository;
import com.academiafitnessapi.repository.InstrutorRepository;
import com.academiafitnessapi.model.Instrutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TreinoService {

    @Autowired
    private TreinoRepository repository;

    @Autowired
    private TreinoMapper mapper;

    @Autowired
    private InstrutorRepository instrutorRepository;

    @Transactional(readOnly = true)
    public org.springframework.data.domain.Page<TreinoResponseDTO> listar(String nome, int page, int size) {
        org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size, org.springframework.data.domain.Sort.by("id").descending());
        if (nome != null && !nome.isBlank()) {
            return repository.findByNomeContainingIgnoreCase(nome, pageable)
                .map(mapper::toResponseDTO);
        }
        return repository.findAll(pageable).map(mapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public TreinoResponseDTO buscar(Long id) {
        Treino entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Treino não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public TreinoResponseDTO criar(TreinoRequestDTO dto) {
        Treino entity = mapper.toEntity(dto);
        Instrutor instrutor = instrutorRepository.findById(dto.getInstrutorId())
            .orElseThrow(() -> new ResourceNotFoundException("Instrutor não encontrado com id: " + dto.getInstrutorId()));
        entity.setInstrutor(instrutor);
        Treino salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public TreinoResponseDTO atualizar(Long id, TreinoRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Treino não encontrado com id: " + id);
        }
        Treino entity = mapper.toEntity(dto);
        entity.setId(id);
        Instrutor instrutor = instrutorRepository.findById(dto.getInstrutorId())
            .orElseThrow(() -> new ResourceNotFoundException("Instrutor não encontrado com id: " + dto.getInstrutorId()));
        entity.setInstrutor(instrutor);
        Treino salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Treino não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
