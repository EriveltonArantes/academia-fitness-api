package com.academiafitnessapi.dto;

import jakarta.validation.constraints.*;

public class ContratoRequestDTO {

    @NotNull(message = "AlunoId é obrigatório")
    @Positive(message = "AlunoId deve ser um ID válido (positivo)")
    private Long alunoId;
    @NotNull(message = "PlanoId é obrigatório")
    @Positive(message = "PlanoId deve ser um ID válido (positivo)")
    private Long planoId;
    @FutureOrPresent(message = "data inicio não pode ser retroativo")
    private java.time.LocalDate dataInicio;
    @FutureOrPresent(message = "data fim não pode ser retroativo")
    private java.time.LocalDate dataFim;
    @DecimalMin(value = "0.0", message = "valor não pode ser negativo")
    @NotNull(message = "valor não pode ser nulo")
    private java.math.BigDecimal valor;
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
    public java.math.BigDecimal getValor() { return valor; }
    public void setValor(java.math.BigDecimal valor) { this.valor = valor; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
