package com.academiafitnessapi.service;

import com.academiafitnessapi.dto.AlunoRequestDTO;
import com.academiafitnessapi.dto.AlunoResponseDTO;
import com.academiafitnessapi.exception.ResourceNotFoundException;
import com.academiafitnessapi.mapper.AlunoMapper;
import com.academiafitnessapi.model.Aluno;
import com.academiafitnessapi.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    @Autowired
    private AlunoMapper mapper;

    public List<AlunoResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    public AlunoResponseDTO buscar(Long id) {
        Aluno entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public AlunoResponseDTO criar(AlunoRequestDTO dto) {
        Aluno entity = mapper.toEntity(dto);
        Aluno salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public AlunoResponseDTO atualizar(Long id, AlunoRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Aluno não encontrado com id: " + id);
        }
        Aluno entity = mapper.toEntity(dto);
        entity.setId(id);
        Aluno salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Aluno não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
