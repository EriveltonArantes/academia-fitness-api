package com.academiafitnessapi.controller;

import com.academiafitnessapi.dto.MatriculaRequestDTO;
import com.academiafitnessapi.dto.MatriculaResponseDTO;
import com.academiafitnessapi.service.MatriculaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Matricula", description = "Gerenciamento de matriculas")
@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService service;

    @Operation(summary = "Listar todos os Matricula")
    @GetMapping
    public ResponseEntity<org.springframework.data.domain.Page<MatriculaResponseDTO>> listar(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(required = false) Long alunoId, @RequestParam(required = false) Long modalidadeId) {
        org.springframework.data.domain.Page<MatriculaResponseDTO> resultado = service.listar(page, size);
        if (alunoId != null) {
            java.util.List<MatriculaResponseDTO> filtrado = resultado.getContent().stream()
                .filter(item -> alunoId.equals(item.getAlunoId()))
                .collect(java.util.stream.Collectors.toList());
            resultado = new org.springframework.data.domain.PageImpl<>(
                filtrado, org.springframework.data.domain.PageRequest.of(page, size), filtrado.size());
        }
        if (modalidadeId != null) {
            java.util.List<MatriculaResponseDTO> filtrado = resultado.getContent().stream()
                .filter(item -> modalidadeId.equals(item.getModalidadeId()))
                .collect(java.util.stream.Collectors.toList());
            resultado = new org.springframework.data.domain.PageImpl<>(
                filtrado, org.springframework.data.domain.PageRequest.of(page, size), filtrado.size());
        }
        return ResponseEntity.ok(resultado);
    }

    @Operation(summary = "Buscar Matricula por ID")
    @GetMapping("/{id}")
    public MatriculaResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @Operation(summary = "Criar Matricula")
    @PostMapping
    public ResponseEntity<MatriculaResponseDTO> criar(@Valid @RequestBody MatriculaRequestDTO matricula) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(matricula));
    }

    @Operation(summary = "Atualizar Matricula")
    @PutMapping("/{id}")
    public MatriculaResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody MatriculaRequestDTO matricula) {
        return service.atualizar(id, matricula);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Excluir Matricula")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
