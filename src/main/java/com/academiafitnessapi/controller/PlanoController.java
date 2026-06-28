package com.academiafitnessapi.controller;

import com.academiafitnessapi.dto.PlanoRequestDTO;
import com.academiafitnessapi.dto.PlanoResponseDTO;
import com.academiafitnessapi.service.PlanoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/planos")
public class PlanoController {

    @Autowired
    private PlanoService service;

    @GetMapping
    public List<PlanoResponseDTO> listar(@RequestParam(required = false) String nome) {
        List<PlanoResponseDTO> resultado = service.listar();
        if (nome != null && !nome.isBlank()) {
            resultado = resultado.stream().filter(item -> item.getNome() != null &&
                item.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
        }
        return resultado;
    }

    @GetMapping("/{id}")
    public PlanoResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @PostMapping
    public ResponseEntity<PlanoResponseDTO> criar(@Valid @RequestBody PlanoRequestDTO plano) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(plano));
    }

    @PutMapping("/{id}")
    public PlanoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody PlanoRequestDTO plano) {
        return service.atualizar(id, plano);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
