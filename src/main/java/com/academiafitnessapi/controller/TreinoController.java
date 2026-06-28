package com.academiafitnessapi.controller;

import com.academiafitnessapi.dto.TreinoRequestDTO;
import com.academiafitnessapi.dto.TreinoResponseDTO;
import com.academiafitnessapi.service.TreinoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Treino", description = "Gerenciamento de treinos")
@RestController
@RequestMapping("/api/treinos")
public class TreinoController {

    @Autowired
    private TreinoService service;

    @Operation(summary = "Listar todos os Treino")
    @GetMapping
    public ResponseEntity<org.springframework.data.domain.Page<TreinoResponseDTO>> listar(@RequestParam(required = false) String nome, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(required = false) Long instrutorId) {
        org.springframework.data.domain.Page<TreinoResponseDTO> resultado = service.listar(nome, page, size);
        if (instrutorId != null) {
            java.util.List<TreinoResponseDTO> filtrado = resultado.getContent().stream()
                .filter(item -> instrutorId.equals(item.getInstrutorId()))
                .collect(java.util.stream.Collectors.toList());
            resultado = new org.springframework.data.domain.PageImpl<>(
                filtrado, org.springframework.data.domain.PageRequest.of(page, size), filtrado.size());
        }
        return ResponseEntity.ok(resultado);
    }

    @Operation(summary = "Buscar Treino por ID")
    @GetMapping("/{id}")
    public TreinoResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @Operation(summary = "Criar Treino")
    @PostMapping
    public ResponseEntity<TreinoResponseDTO> criar(@Valid @RequestBody TreinoRequestDTO treino) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(treino));
    }

    @Operation(summary = "Atualizar Treino")
    @PutMapping("/{id}")
    public TreinoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody TreinoRequestDTO treino) {
        return service.atualizar(id, treino);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Excluir Treino")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
