package com.academiafitnessapi.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "matriculas")
public class Matricula {

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
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;
    @ManyToOne
    @JoinColumn(name = "modalidade_id")
    private Modalidade modalidade;
    private java.time.LocalDateTime dataInicio;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public java.time.LocalDateTime getCriadoEm() { return criadoEm; }
    public java.time.LocalDateTime getAtualizadoEm() { return atualizadoEm; }

    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }
    public Modalidade getModalidade() { return modalidade; }
    public void setModalidade(Modalidade modalidade) { this.modalidade = modalidade; }
    public java.time.LocalDateTime getDataInicio() { return dataInicio; }
    public void setDataInicio(java.time.LocalDateTime dataInicio) { this.dataInicio = dataInicio; }
}
