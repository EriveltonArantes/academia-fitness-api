package com.academiafitnessapi.dto;

import jakarta.validation.constraints.*;

public class PlanoRequestDTO {

    @NotBlank(message = "nome não pode estar em branco")
    private String nome;
    @NotBlank(message = "descricao não pode estar em branco")
    private String descricao;
    @NotNull(message = "valor não pode ser nulo")
    private Double valor;
    @NotNull(message = "duracao não pode ser nulo")
    private Integer duracao;
    @NotBlank(message = "tipo pagamento não pode estar em branco")
    private String tipoPagamento;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }
    public Integer getDuracao() { return duracao; }
    public void setDuracao(Integer duracao) { this.duracao = duracao; }
    public String getTipoPagamento() { return tipoPagamento; }
    public void setTipoPagamento(String tipoPagamento) { this.tipoPagamento = tipoPagamento; }
}
