package com.academiafitnessapi.dto;

import jakarta.validation.constraints.*;

public class ContratoRequestDTO {

    @NotNull(message = "aluno não pode ser nulo")
    private Long alunoId;
    @NotNull(message = "plano não pode ser nulo")
    private Long planoId;
    @FutureOrPresent(message = "data inicio não pode ser retroativo")
    @NotNull(message = "data inicio não pode ser nulo")
    private java.time.LocalDate dataInicio;
    @FutureOrPresent(message = "data fim não pode ser retroativo")
    @NotNull(message = "data fim não pode ser nulo")
    private java.time.LocalDate dataFim;
    @NotNull(message = "valor não pode ser nulo")
    private Double valor;
    @NotBlank(message = "status não pode estar em branco")
    private String status;

    public Long getAlunoId() { return alunoId; }
    public void setAlunoId(Long alunoId) { this.alunoId = alunoId; }
    public Long getPlanoId() { return planoId; }
    public void setPlanoId(Long planoId) { this.planoId = planoId; }
    public java.time.LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(java.time.LocalDate dataInicio) { this.dataInicio = dataInicio; }
    public java.time.LocalDate getDataFim() { return dataFim; }
    public void setDataFim(java.time.LocalDate dataFim) { this.dataFim = dataFim; }
    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
