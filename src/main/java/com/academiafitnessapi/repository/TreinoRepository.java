package com.academiafitnessapi.repository;

import com.academiafitnessapi.model.Treino;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreinoRepository extends JpaRepository<Treino, Long> {

    org.springframework.data.domain.Page<Treino> findByNomeContainingIgnoreCase(String nome, org.springframework.data.domain.Pageable pageable);
}
