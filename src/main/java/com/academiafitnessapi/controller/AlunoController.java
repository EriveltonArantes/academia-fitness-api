package com.academiafitnessapi.controller;

import com.academiafitnessapi.dto.AlunoRequestDTO;
import com.academiafitnessapi.dto.AlunoResponseDTO;
import com.academiafitnessapi.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoService service;

    @GetMapping
    public List<AlunoResponseDTO> listar(@RequestParam(required = false) String nome) {
        List<AlunoResponseDTO> resultado = service.listar();
        if (nome != null && !nome.isBlank()) {
            resultado = resultado.stream().filter(item -> item.getNome() != null &&
                item.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
        }
        return resultado;
    }

    @GetMapping("/{id}")
    public AlunoResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @PostMapping
    public ResponseEntity<AlunoResponseDTO> criar(@Valid @RequestBody AlunoRequestDTO aluno) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(aluno));
    }

    @PutMapping("/{id}")
    public AlunoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody AlunoRequestDTO aluno) {
        return service.atualizar(id, aluno);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
