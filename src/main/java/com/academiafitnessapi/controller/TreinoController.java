package com.academiafitnessapi.controller;

import com.academiafitnessapi.dto.TreinoRequestDTO;
import com.academiafitnessapi.dto.TreinoResponseDTO;
import com.academiafitnessapi.service.TreinoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/treinos")
public class TreinoController {

    @Autowired
    private TreinoService service;

    @GetMapping
    public List<TreinoResponseDTO> listar(@RequestParam(required = false) String nome, @RequestParam(required = false) Long instrutorId) {
        List<TreinoResponseDTO> resultado = service.listar();
        if (nome != null && !nome.isBlank()) {
            resultado = resultado.stream().filter(item -> item.getNome() != null &&
                item.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
        }
        if (instrutorId != null) {
            resultado = resultado.stream().filter(item -> instrutorId.equals(item.getInstrutorId())).collect(java.util.stream.Collectors.toList());
        }
        return resultado;
    }

    @GetMapping("/{id}")
    public TreinoResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @PostMapping
    public ResponseEntity<TreinoResponseDTO> criar(@Valid @RequestBody TreinoRequestDTO treino) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(treino));
    }

    @PutMapping("/{id}")
    public TreinoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody TreinoRequestDTO treino) {
        return service.atualizar(id, treino);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
