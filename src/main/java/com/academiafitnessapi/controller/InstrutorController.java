package com.academiafitnessapi.controller;

import com.academiafitnessapi.dto.InstrutorRequestDTO;
import com.academiafitnessapi.dto.InstrutorResponseDTO;
import com.academiafitnessapi.service.InstrutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/instrutors")
public class InstrutorController {

    @Autowired
    private InstrutorService service;

    @GetMapping
    public List<InstrutorResponseDTO> listar(@RequestParam(required = false) String nome, @RequestParam(required = false) Long alunosAtivosId, @RequestParam(required = false) Long contratosVencendoId, @RequestParam(required = false) Long planosAssociadosId) {
        List<InstrutorResponseDTO> resultado = service.listar();
        if (nome != null && !nome.isBlank()) {
            resultado = resultado.stream().filter(item -> item.getNome() != null &&
                item.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
        }
        if (alunosAtivosId != null) {
            resultado = resultado.stream().filter(item -> alunosAtivosId.equals(item.getAlunosAtivosId())).collect(java.util.stream.Collectors.toList());
        }
        if (contratosVencendoId != null) {
            resultado = resultado.stream().filter(item -> contratosVencendoId.equals(item.getContratosVencendoId())).collect(java.util.stream.Collectors.toList());
        }
        if (planosAssociadosId != null) {
            resultado = resultado.stream().filter(item -> planosAssociadosId.equals(item.getPlanosAssociadosId())).collect(java.util.stream.Collectors.toList());
        }
        return resultado;
    }

    @GetMapping("/{id}")
    public InstrutorResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @PostMapping
    public ResponseEntity<InstrutorResponseDTO> criar(@Valid @RequestBody InstrutorRequestDTO instrutor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(instrutor));
    }

    @PutMapping("/{id}")
    public InstrutorResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody InstrutorRequestDTO instrutor) {
        return service.atualizar(id, instrutor);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
