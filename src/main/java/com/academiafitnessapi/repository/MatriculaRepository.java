package com.academiafitnessapi.repository;

import com.academiafitnessapi.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    org.springframework.data.domain.Page<Matricula> findByAlunoContainingIgnoreCase(String aluno, org.springframework.data.domain.Pageable pageable);
}
