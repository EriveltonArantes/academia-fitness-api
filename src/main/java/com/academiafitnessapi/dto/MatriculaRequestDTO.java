package com.academiafitnessapi.dto;

import jakarta.validation.constraints.*;

public class MatriculaRequestDTO {

    @NotNull(message = "AlunoId é obrigatório")
    @Positive(message = "AlunoId deve ser um ID válido (positivo)")
    private Long alunoId;
    @NotNull(message = "ModalidadeId é obrigatório")
    @Positive(message = "ModalidadeId deve ser um ID válido (positivo)")
    private Long modalidadeId;
    @FutureOrPresent(message = "data inicio não pode ser retroativo")
    private java.time.LocalDateTime dataInicio;

    public Long getAlunoId() { return alunoId; }
    public void setAlunoId(Long alunoId) { this.alunoId = alunoId; }
    public Long getModalidadeId() { return modalidadeId; }
    public void setModalidadeId(Long modalidadeId) { this.modalidadeId = modalidadeId; }
    public java.time.LocalDateTime getDataInicio() { return dataInicio; }
    public void setDataInicio(java.time.LocalDateTime dataInicio) { this.dataInicio = dataInicio; }
}
