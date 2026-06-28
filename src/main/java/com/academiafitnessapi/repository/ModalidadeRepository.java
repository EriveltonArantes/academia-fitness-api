package com.academiafitnessapi.repository;

import com.academiafitnessapi.model.Modalidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModalidadeRepository extends JpaRepository<Modalidade, Long> {

    org.springframework.data.domain.Page<Modalidade> findByNomeContainingIgnoreCase(String nome, org.springframework.data.domain.Pageable pageable);
}
