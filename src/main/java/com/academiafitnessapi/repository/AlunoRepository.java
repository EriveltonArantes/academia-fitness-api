package com.academiafitnessapi.repository;

import com.academiafitnessapi.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    boolean existsByCpf(String cpf);
    boolean existsByCpfAndIdNot(String cpf, Long id);

    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);

    org.springframework.data.domain.Page<Aluno> findByNomeContainingIgnoreCase(String nome, org.springframework.data.domain.Pageable pageable);
}
