package com.academiafitnessapi.dto;

import jakarta.validation.constraints.*;

public class TreinoRequestDTO {

    @NotNull(message = "InstrutorId é obrigatório")
    @Positive(message = "InstrutorId deve ser um ID válido (positivo)")
    private Long instrutorId;
    @NotBlank(message = "nome não pode estar em branco")
    private String nome;
    @NotBlank(message = "descricao não pode estar em branco")
    private String descricao;
    @NotBlank(message = "turno não pode estar em branco")
    private String turno;
    @NotNull(message = "duracao não pode ser nulo")
    private Integer duracao;
    @NotBlank(message = "nivel dificuldade não pode estar em branco")
    private String nivelDificuldade;

    public Long getInstrutorId() { return instrutorId; }
    public void setInstrutorId(Long instrutorId) { this.instrutorId = instrutorId; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }
    public Integer getDuracao() { return duracao; }
    public void setDuracao(Integer duracao) { this.duracao = duracao; }
    public String getNivelDificuldade() { return nivelDificuldade; }
    public void setNivelDificuldade(String nivelDificuldade) { this.nivelDificuldade = nivelDificuldade; }
}
