package com.academiafitnessapi.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "contratos")
public class Contrato {

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
    @JoinColumn(name = "plano_id")
    private Plano plano;
    private java.time.LocalDate dataInicio;
    private java.time.LocalDate dataFim;
    private java.math.BigDecimal valor;
    @Column(nullable = false)
    private String status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public java.time.LocalDateTime getCriadoEm() { return criadoEm; }
    public java.time.LocalDateTime getAtualizadoEm() { return atualizadoEm; }

    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }
    public Plano getPlano() { return plano; }
    public void setPlano(Plano plano) { this.plano = plano; }
    public java.time.LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(java.time.LocalDate dataInicio) { this.dataInicio = dataInicio; }
    public java.time.LocalDate getDataFim() { return dataFim; }
    public void setDataFim(java.time.LocalDate dataFim) { this.dataFim = dataFim; }
    public java.math.BigDecimal getValor() { return valor; }
    public void setValor(java.math.BigDecimal valor) { this.valor = valor; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
