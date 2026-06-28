package com.academiafitnessapi.service;

import com.academiafitnessapi.dto.ContratoRequestDTO;
import com.academiafitnessapi.dto.ContratoResponseDTO;
import com.academiafitnessapi.exception.ResourceNotFoundException;
import com.academiafitnessapi.mapper.ContratoMapper;
import com.academiafitnessapi.model.Contrato;
import com.academiafitnessapi.repository.ContratoRepository;
import com.academiafitnessapi.repository.AlunoRepository;
import com.academiafitnessapi.model.Aluno;
import com.academiafitnessapi.repository.PlanoRepository;
import com.academiafitnessapi.model.Plano;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ContratoService {

    @Autowired
    private ContratoRepository repository;

    @Autowired
    private ContratoMapper mapper;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private PlanoRepository planoRepository;

    public List<ContratoResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    public ContratoResponseDTO buscar(Long id) {
        Contrato entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Contrato não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public ContratoResponseDTO criar(ContratoRequestDTO dto) {
        Contrato entity = mapper.toEntity(dto);
        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
            .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com id: " + dto.getAlunoId()));
        entity.setAluno(aluno);
        Plano plano = planoRepository.findById(dto.getPlanoId())
            .orElseThrow(() -> new ResourceNotFoundException("Plano não encontrado com id: " + dto.getPlanoId()));
        entity.setPlano(plano);
        Contrato salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public ContratoResponseDTO atualizar(Long id, ContratoRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Contrato não encontrado com id: " + id);
        }
        Contrato entity = mapper.toEntity(dto);
        entity.setId(id);
        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
            .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com id: " + dto.getAlunoId()));
        entity.setAluno(aluno);
        Plano plano = planoRepository.findById(dto.getPlanoId())
            .orElseThrow(() -> new ResourceNotFoundException("Plano não encontrado com id: " + dto.getPlanoId()));
        entity.setPlano(plano);
        Contrato salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Contrato não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
