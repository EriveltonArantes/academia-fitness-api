package com.academiafitnessapi.service;

import com.academiafitnessapi.dto.MatriculaRequestDTO;
import com.academiafitnessapi.dto.MatriculaResponseDTO;
import com.academiafitnessapi.exception.ResourceNotFoundException;
import com.academiafitnessapi.mapper.MatriculaMapper;
import com.academiafitnessapi.model.Matricula;
import com.academiafitnessapi.repository.MatriculaRepository;
import com.academiafitnessapi.repository.AlunoRepository;
import com.academiafitnessapi.model.Aluno;
import com.academiafitnessapi.repository.ModalidadeRepository;
import com.academiafitnessapi.model.Modalidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MatriculaService {

    @Autowired
    private MatriculaRepository repository;

    @Autowired
    private MatriculaMapper mapper;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ModalidadeRepository modalidadeRepository;

    @Transactional(readOnly = true)
    public org.springframework.data.domain.Page<MatriculaResponseDTO> listar(String aluno, int page, int size) {
        org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size, org.springframework.data.domain.Sort.by("id").descending());
        if (aluno != null && !aluno.isBlank()) {
            return repository.findByAlunoContainingIgnoreCase(aluno, pageable)
                .map(mapper::toResponseDTO);
        }
        return repository.findAll(pageable).map(mapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public MatriculaResponseDTO buscar(Long id) {
        Matricula entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Matricula não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public MatriculaResponseDTO criar(MatriculaRequestDTO dto) {
        Matricula entity = mapper.toEntity(dto);
        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
            .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com id: " + dto.getAlunoId()));
        entity.setAluno(aluno);
        Modalidade modalidade = modalidadeRepository.findById(dto.getModalidadeId())
            .orElseThrow(() -> new ResourceNotFoundException("Modalidade não encontrado com id: " + dto.getModalidadeId()));
        entity.setModalidade(modalidade);
        Matricula salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public MatriculaResponseDTO atualizar(Long id, MatriculaRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Matricula não encontrado com id: " + id);
        }
        Matricula entity = mapper.toEntity(dto);
        entity.setId(id);
        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
            .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com id: " + dto.getAlunoId()));
        entity.setAluno(aluno);
        Modalidade modalidade = modalidadeRepository.findById(dto.getModalidadeId())
            .orElseThrow(() -> new ResourceNotFoundException("Modalidade não encontrado com id: " + dto.getModalidadeId()));
        entity.setModalidade(modalidade);
        Matricula salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Matricula não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
