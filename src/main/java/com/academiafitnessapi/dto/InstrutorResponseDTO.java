package com.academiafitnessapi.dto;

public class InstrutorResponseDTO {

    private Long id;
    private Long alunosAtivosId;
    private Long contratosVencendoId;
    private Long planosAssociadosId;
    private String nome;
    private String cpf;
    private String email;
    private String cref;
    private String especialidade;
    private String turno;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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
