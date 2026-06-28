package com.academiafitnessapi.controller;

import com.academiafitnessapi.dto.ContratoRequestDTO;
import com.academiafitnessapi.dto.ContratoResponseDTO;
import com.academiafitnessapi.service.ContratoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Contrato", description = "Gerenciamento de contratos")
@RestController
@RequestMapping("/api/contratos")
public class ContratoController {

    @Autowired
    private ContratoService service;

    @Operation(summary = "Listar todos os Contrato")
    @GetMapping
    public ResponseEntity<org.springframework.data.domain.Page<ContratoResponseDTO>> listar(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(required = false) Long alunoId, @RequestParam(required = false) Long planoId) {
        org.springframework.data.domain.Page<ContratoResponseDTO> resultado = service.listar(page, size);
        if (alunoId != null) {
            java.util.List<ContratoResponseDTO> filtrado = resultado.getContent().stream()
                .filter(item -> alunoId.equals(item.getAlunoId()))
                .collect(java.util.stream.Collectors.toList());
            resultado = new org.springframework.data.domain.PageImpl<>(
                filtrado, org.springframework.data.domain.PageRequest.of(page, size), filtrado.size());
        }
        if (planoId != null) {
            java.util.List<ContratoResponseDTO> filtrado = resultado.getContent().stream()
                .filter(item -> planoId.equals(item.getPlanoId()))
                .collect(java.util.stream.Collectors.toList());
            resultado = new org.springframework.data.domain.PageImpl<>(
                filtrado, org.springframework.data.domain.PageRequest.of(page, size), filtrado.size());
        }
        return ResponseEntity.ok(resultado);
    }

    @Operation(summary = "Buscar Contrato por ID")
    @GetMapping("/{id}")
    public ContratoResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @Operation(summary = "Criar Contrato")
    @PostMapping
    public ResponseEntity<ContratoResponseDTO> criar(@Valid @RequestBody ContratoRequestDTO contrato) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(contrato));
    }

    @Operation(summary = "Atualizar Contrato")
    @PutMapping("/{id}")
    public ContratoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody ContratoRequestDTO contrato) {
        return service.atualizar(id, contrato);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Excluir Contrato")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
