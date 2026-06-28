package com.academiafitnessapi.dto;

import jakarta.validation.constraints.*;

public class AlunoRequestDTO {

    @NotBlank(message = "nome não pode estar em branco")
    private String nome;
    @NotBlank(message = "cpf não pode estar em branco")
    private String cpf;
    @NotBlank(message = "email não pode estar em branco")
    @Email(message = "email precisa ser um e-mail válido")
    private String email;
    @NotBlank(message = "telefone não pode estar em branco")
    private String telefone;
    @NotNull(message = "data nascimento não pode ser nulo")
    private java.time.LocalDate dataNascimento;
    @NotBlank(message = "status matricula não pode estar em branco")
    private String statusMatricula;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public java.time.LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(java.time.LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    public String getStatusMatricula() { return statusMatricula; }
    public void setStatusMatricula(String statusMatricula) { this.statusMatricula = statusMatricula; }
}
