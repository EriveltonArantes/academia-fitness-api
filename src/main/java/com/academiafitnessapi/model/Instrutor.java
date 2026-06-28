package com.academiafitnessapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "instrutors")
public class Instrutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "alunosAtivos_id")
    private Aluno alunosAtivos;
    @ManyToOne
    @JoinColumn(name = "contratosVencendo_id")
    private Contrato contratosVencendo;
    @ManyToOne
    @JoinColumn(name = "planosAssociados_id")
    private Plano planosAssociados;
    private String nome;
    @Column(unique = true)
    private String cpf;
    @Column(unique = true)
    private String email;
    private String cref;
    private String especialidade;
    private String turno;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Aluno getAlunosAtivos() { return alunosAtivos; }
    public void setAlunosAtivos(Aluno alunosAtivos) { this.alunosAtivos = alunosAtivos; }
    public Contrato getContratosVencendo() { return contratosVencendo; }
    public void setContratosVencendo(Contrato contratosVencendo) { this.contratosVencendo = contratosVencendo; }
    public Plano getPlanosAssociados() { return planosAssociados; }
    public void setPlanosAssociados(Plano planosAssociados) { this.planosAssociados = planosAssociados; }
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
