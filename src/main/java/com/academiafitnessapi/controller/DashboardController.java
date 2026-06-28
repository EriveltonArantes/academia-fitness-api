package com.academiafitnessapi.controller;

import com.academiafitnessapi.model.Aluno;
import com.academiafitnessapi.model.Plano;
import com.academiafitnessapi.model.Contrato;
import com.academiafitnessapi.model.Treino;
import com.academiafitnessapi.model.Instrutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private com.academiafitnessapi.repository.AlunoRepository alunoRepository;

    @Autowired
    private com.academiafitnessapi.repository.PlanoRepository planoRepository;

    @Autowired
    private com.academiafitnessapi.repository.ContratoRepository contratoRepository;

    @Autowired
    private com.academiafitnessapi.repository.TreinoRepository treinoRepository;

    @Autowired
    private com.academiafitnessapi.repository.InstrutorRepository instrutorRepository;

    @GetMapping("/resumo")
    public Map<String, Object> resumo() {
        Map<String, Object> resumo = new LinkedHashMap<>();
        resumo.put("totalAluno", alunoRepository.count());
        resumo.put("totalPlano", planoRepository.count());
        resumo.put("somaValorPlano", planoRepository.findAll().stream().mapToDouble(Plano::getValor).sum());
        resumo.put("totalContrato", contratoRepository.count());
        resumo.put("somaValorContrato", contratoRepository.findAll().stream().mapToDouble(Contrato::getValor).sum());
        resumo.put("graficoContrato", contratoRepository.findAll().stream()
            .collect(java.util.stream.Collectors.groupingBy(
                item -> String.valueOf(item.getStatus()),
                java.util.LinkedHashMap::new,
                java.util.stream.Collectors.counting())));
        resumo.put("totalTreino", treinoRepository.count());
        resumo.put("somaDuracaoTreino", treinoRepository.findAll().stream().mapToInt(Treino::getDuracao).sum());
        resumo.put("totalInstrutor", instrutorRepository.count());
        return resumo;
    }
}
