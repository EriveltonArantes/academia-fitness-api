package com.academiafitnessapi.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "planos")
public class Plano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "criado_em", updatable = false)
    private java.time.LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em")
    private java.time.LocalDateTime atualizadoEm;

    @Column(nullable = false)
    private String nome;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String descricao;
    private java.math.BigDecimal valor;
    private Integer duracao;
    @Column(nullable = false)
    private String tipoPagamento;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public java.time.LocalDateTime getCriadoEm() { return criadoEm; }
    public java.time.LocalDateTime getAtualizadoEm() { return atualizadoEm; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public java.math.BigDecimal getValor() { return valor; }
    public void setValor(java.math.BigDecimal valor) { this.valor = valor; }
    public Integer getDuracao() { return duracao; }
    public void setDuracao(Integer duracao) { this.duracao = duracao; }
    public String getTipoPagamento() { return tipoPagamento; }
    public void setTipoPagamento(String tipoPagamento) { this.tipoPagamento = tipoPagamento; }
}
