package com.academiafitnessapi.dto;

public class ContratoResponseDTO {

    private Long id;
    private java.time.LocalDateTime criadoEm;
    private java.time.LocalDateTime atualizadoEm;
    private Long alunoId;
    private Long planoId;
    private java.time.LocalDate dataInicio;
    private java.time.LocalDate dataFim;
    private java.math.BigDecimal valor;
    private String status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public java.time.LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(java.time.LocalDateTime v) { this.criadoEm = v; }
    public java.time.LocalDateTime getAtualizadoEm() { return atualizadoEm; }
    public void setAtualizadoEm(java.time.LocalDateTime v) { this.atualizadoEm = v; }
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
