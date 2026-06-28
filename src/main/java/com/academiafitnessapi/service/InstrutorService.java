package com.academiafitnessapi.service;

import com.academiafitnessapi.dto.InstrutorRequestDTO;
import com.academiafitnessapi.dto.InstrutorResponseDTO;
import com.academiafitnessapi.exception.ResourceNotFoundException;
import com.academiafitnessapi.mapper.InstrutorMapper;
import com.academiafitnessapi.model.Instrutor;
import com.academiafitnessapi.repository.InstrutorRepository;
import com.academiafitnessapi.repository.AlunoRepository;
import com.academiafitnessapi.model.Aluno;
import com.academiafitnessapi.repository.ContratoRepository;
import com.academiafitnessapi.model.Contrato;
import com.academiafitnessapi.repository.PlanoRepository;
import com.academiafitnessapi.model.Plano;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InstrutorService {

    @Autowired
    private InstrutorRepository repository;

    @Autowired
    private InstrutorMapper mapper;

    @Autowired
    private AlunoRepository alunosAtivosRepository;

    @Autowired
    private ContratoRepository contratosVencendoRepository;

    @Autowired
    private PlanoRepository planosAssociadosRepository;

    public List<InstrutorResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    public InstrutorResponseDTO buscar(Long id) {
        Instrutor entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Instrutor não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public InstrutorResponseDTO criar(InstrutorRequestDTO dto) {
        Instrutor entity = mapper.toEntity(dto);
        Aluno alunosAtivos = alunosAtivosRepository.findById(dto.getAlunosAtivosId())
            .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com id: " + dto.getAlunosAtivosId()));
        entity.setAlunosAtivos(alunosAtivos);
        Contrato contratosVencendo = contratosVencendoRepository.findById(dto.getContratosVencendoId())
            .orElseThrow(() -> new ResourceNotFoundException("Contrato não encontrado com id: " + dto.getContratosVencendoId()));
        entity.setContratosVencendo(contratosVencendo);
        Plano planosAssociados = planosAssociadosRepository.findById(dto.getPlanosAssociadosId())
            .orElseThrow(() -> new ResourceNotFoundException("Plano não encontrado com id: " + dto.getPlanosAssociadosId()));
        entity.setPlanosAssociados(planosAssociados);
        Instrutor salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public InstrutorResponseDTO atualizar(Long id, InstrutorRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Instrutor não encontrado com id: " + id);
        }
        Instrutor entity = mapper.toEntity(dto);
        entity.setId(id);
        Aluno alunosAtivos = alunosAtivosRepository.findById(dto.getAlunosAtivosId())
            .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com id: " + dto.getAlunosAtivosId()));
        entity.setAlunosAtivos(alunosAtivos);
        Contrato contratosVencendo = contratosVencendoRepository.findById(dto.getContratosVencendoId())
            .orElseThrow(() -> new ResourceNotFoundException("Contrato não encontrado com id: " + dto.getContratosVencendoId()));
        entity.setContratosVencendo(contratosVencendo);
        Plano planosAssociados = planosAssociadosRepository.findById(dto.getPlanosAssociadosId())
            .orElseThrow(() -> new ResourceNotFoundException("Plano não encontrado com id: " + dto.getPlanosAssociadosId()));
        entity.setPlanosAssociados(planosAssociados);
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
