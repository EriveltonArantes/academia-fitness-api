package com.academiafitnessapi.service;

import com.academiafitnessapi.dto.AlunoRequestDTO;
import com.academiafitnessapi.dto.AlunoResponseDTO;
import com.academiafitnessapi.exception.ResourceNotFoundException;
import com.academiafitnessapi.exception.BusinessException;
import com.academiafitnessapi.mapper.AlunoMapper;
import com.academiafitnessapi.model.Aluno;
import com.academiafitnessapi.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    @Autowired
    private AlunoMapper mapper;

    @Transactional(readOnly = true)
    public org.springframework.data.domain.Page<AlunoResponseDTO> listar(String nome, int page, int size) {
        org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size, org.springframework.data.domain.Sort.by("id").descending());
        if (nome != null && !nome.isBlank()) {
            return repository.findByNomeContainingIgnoreCase(nome, pageable)
                .map(mapper::toResponseDTO);
        }
        return repository.findAll(pageable).map(mapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public AlunoResponseDTO buscar(Long id) {
        Aluno entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public AlunoResponseDTO criar(AlunoRequestDTO dto) {
        if (repository.existsByCpf(dto.getCpf())) {
            throw new BusinessException("Cpf já cadastrado: " + dto.getCpf());
        }
        if (repository.existsByEmail(dto.getEmail())) {
            throw new BusinessException("Email já cadastrado: " + dto.getEmail());
        }
        Aluno entity = mapper.toEntity(dto);
        Aluno salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public AlunoResponseDTO atualizar(Long id, AlunoRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Aluno não encontrado com id: " + id);
        }
        if (repository.existsByCpfAndIdNot(dto.getCpf(), id)) {
            throw new BusinessException("Cpf já cadastrado em outro registro: " + dto.getCpf());
        }
        if (repository.existsByEmailAndIdNot(dto.getEmail(), id)) {
            throw new BusinessException("Email já cadastrado em outro registro: " + dto.getEmail());
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
