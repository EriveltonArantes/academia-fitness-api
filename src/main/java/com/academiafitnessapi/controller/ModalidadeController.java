package com.academiafitnessapi.controller;

import com.academiafitnessapi.dto.ModalidadeRequestDTO;
import com.academiafitnessapi.dto.ModalidadeResponseDTO;
import com.academiafitnessapi.service.ModalidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Modalidade", description = "Gerenciamento de modalidades")
@RestController
@RequestMapping("/api/modalidades")
public class ModalidadeController {

    @Autowired
    private ModalidadeService service;

    @Operation(summary = "Listar todos os Modalidade")
    @GetMapping
    public ResponseEntity<org.springframework.data.domain.Page<ModalidadeResponseDTO>> listar(@RequestParam(required = false) String nome, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(service.listar(nome, page, size));
    }

    @Operation(summary = "Buscar Modalidade por ID")
    @GetMapping("/{id}")
    public ModalidadeResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @Operation(summary = "Criar Modalidade")
    @PostMapping
    public ResponseEntity<ModalidadeResponseDTO> criar(@Valid @RequestBody ModalidadeRequestDTO modalidade) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(modalidade));
    }

    @Operation(summary = "Atualizar Modalidade")
    @PutMapping("/{id}")
    public ModalidadeResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody ModalidadeRequestDTO modalidade) {
        return service.atualizar(id, modalidade);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Excluir Modalidade")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
