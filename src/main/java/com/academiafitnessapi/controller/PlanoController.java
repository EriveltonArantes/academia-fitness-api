package com.academiafitnessapi.controller;

import com.academiafitnessapi.dto.PlanoRequestDTO;
import com.academiafitnessapi.dto.PlanoResponseDTO;
import com.academiafitnessapi.service.PlanoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Plano", description = "Gerenciamento de planos")
@RestController
@RequestMapping("/api/planos")
public class PlanoController {

    @Autowired
    private PlanoService service;

    @Operation(summary = "Listar todos os Plano")
    @GetMapping
    public ResponseEntity<org.springframework.data.domain.Page<PlanoResponseDTO>> listar(@RequestParam(required = false) String nome, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(service.listar(nome, page, size));
    }

    @Operation(summary = "Buscar Plano por ID")
    @GetMapping("/{id}")
    public PlanoResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @Operation(summary = "Criar Plano")
    @PostMapping
    public ResponseEntity<PlanoResponseDTO> criar(@Valid @RequestBody PlanoRequestDTO plano) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(plano));
    }

    @Operation(summary = "Atualizar Plano")
    @PutMapping("/{id}")
    public PlanoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody PlanoRequestDTO plano) {
        return service.atualizar(id, plano);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Excluir Plano")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
