package com.academiafitnessapi.dto;

import jakarta.validation.constraints.*;

public class InstrutorRequestDTO {

    @NotNull(message = "alunos ativos não pode ser nulo")
    private Long alunosAtivosId;
    @NotNull(message = "contratos vencendo não pode ser nulo")
    private Long contratosVencendoId;
    @NotNull(message = "planos associados não pode ser nulo")
    private Long planosAssociadosId;
    @NotBlank(message = "nome não pode estar em branco")
    private String nome;
    @NotBlank(message = "cpf não pode estar em branco")
    private String cpf;
    @NotBlank(message = "email não pode estar em branco")
    @Email(message = "email precisa ser um e-mail válido")
    private String email;
    @NotBlank(message = "cref não pode estar em branco")
    private String cref;
    @NotBlank(message = "especialidade não pode estar em branco")
    private String especialidade;
    @NotBlank(message = "turno não pode estar em branco")
    private String turno;

    public Long getAlunosAtivosId() { return alunosAtivosId; }
    public void setAlunosAtivosId(Long alunosAtivosId) { this.alunosAtivosId = alunosAtivosId; }
    public Long getContratosVencendoId() { return contratosVencendoId; }
    public void setContratosVencendoId(Long contratosVencendoId) { this.contratosVencendoId = contratosVencendoId; }
    public Long getPlanosAssociadosId() { return planosAssociadosId; }
    public void setPlanosAssociadosId(Long planosAssociadosId) { this.planosAssociadosId = planosAssociadosId; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCref() { return cref; }
    public void setCref(String cref) { this.cref = cref; }
    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }
    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }
}
