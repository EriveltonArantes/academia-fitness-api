package com.academiafitnessapi.controller;

import com.academiafitnessapi.dto.ContratoRequestDTO;
import com.academiafitnessapi.dto.ContratoResponseDTO;
import com.academiafitnessapi.service.ContratoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/contratos")
public class ContratoController {

    @Autowired
    private ContratoService service;

    @GetMapping
    public List<ContratoResponseDTO> listar(@RequestParam(required = false) Long alunoId, @RequestParam(required = false) Long planoId) {
        List<ContratoResponseDTO> resultado = service.listar();
        if (alunoId != null) {
            resultado = resultado.stream().filter(item -> alunoId.equals(item.getAlunoId())).collect(java.util.stream.Collectors.toList());
        }
        if (planoId != null) {
            resultado = resultado.stream().filter(item -> planoId.equals(item.getPlanoId())).collect(java.util.stream.Collectors.toList());
        }
        return resultado;
    }

    @GetMapping("/{id}")
    public ContratoResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @PostMapping
    public ResponseEntity<ContratoResponseDTO> criar(@Valid @RequestBody ContratoRequestDTO contrato) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(contrato));
    }

    @PutMapping("/{id}")
    public ContratoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody ContratoRequestDTO contrato) {
        return service.atualizar(id, contrato);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
