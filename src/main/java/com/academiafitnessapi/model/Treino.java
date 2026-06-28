package com.academiafitnessapi.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "treinos")
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "criado_em", updatable = false)
    private java.time.LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em")
    private java.time.LocalDateTime atualizadoEm;

    @ManyToOne
    @JoinColumn(name = "instrutor_id")
    private Instrutor instrutor;
    @Column(nullable = false)
    private String nome;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String descricao;
    @Column(nullable = false)
    private String turno;
    private Integer duracao;
    @Column(nullable = false)
    private String nivelDificuldade;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public java.time.LocalDateTime getCriadoEm() { return criadoEm; }
    public java.time.LocalDateTime getAtualizadoEm() { return atualizadoEm; }

    public Instrutor getInstrutor() { return instrutor; }
    public void setInstrutor(Instrutor instrutor) { this.instrutor = instrutor; }
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
