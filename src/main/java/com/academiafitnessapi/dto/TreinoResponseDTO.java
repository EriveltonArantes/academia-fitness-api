package com.academiafitnessapi.dto;

public class TreinoResponseDTO {

    private Long id;
    private java.time.LocalDateTime criadoEm;
    private java.time.LocalDateTime atualizadoEm;
    private Long instrutorId;
    private String nome;
    private String descricao;
    private String turno;
    private Integer duracao;
    private String nivelDificuldade;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public java.time.LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(java.time.LocalDateTime v) { this.criadoEm = v; }
    public java.time.LocalDateTime getAtualizadoEm() { return atualizadoEm; }
    public void setAtualizadoEm(java.time.LocalDateTime v) { this.atualizadoEm = v; }
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
