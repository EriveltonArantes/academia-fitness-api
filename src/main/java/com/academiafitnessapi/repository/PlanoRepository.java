package com.academiafitnessapi.repository;

import com.academiafitnessapi.model.Plano;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanoRepository extends JpaRepository<Plano, Long> {

    org.springframework.data.domain.Page<Plano> findByNomeContainingIgnoreCase(String nome, org.springframework.data.domain.Pageable pageable);
}
