package com.academiafitnessapi.controller;

import com.academiafitnessapi.dto.InstrutorRequestDTO;
import com.academiafitnessapi.dto.InstrutorResponseDTO;
import com.academiafitnessapi.service.InstrutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Instrutor", description = "Gerenciamento de instrutors")
@RestController
@RequestMapping("/api/instrutors")
public class InstrutorController {

    @Autowired
    private InstrutorService service;

    @Operation(summary = "Listar todos os Instrutor")
    @GetMapping
    public ResponseEntity<org.springframework.data.domain.Page<InstrutorResponseDTO>> listar(@RequestParam(required = false) String nome, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(service.listar(nome, page, size));
    }

    @Operation(summary = "Buscar Instrutor por ID")
    @GetMapping("/{id}")
    public InstrutorResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @Operation(summary = "Criar Instrutor")
    @PostMapping
    public ResponseEntity<InstrutorResponseDTO> criar(@Valid @RequestBody InstrutorRequestDTO instrutor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(instrutor));
    }

    @Operation(summary = "Atualizar Instrutor")
    @PutMapping("/{id}")
    public InstrutorResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody InstrutorRequestDTO instrutor) {
        return service.atualizar(id, instrutor);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Excluir Instrutor")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
