package com.academiafitnessapi.repository;

import com.academiafitnessapi.model.Instrutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrutorRepository extends JpaRepository<Instrutor, Long> {

    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);

    org.springframework.data.domain.Page<Instrutor> findByNomeContainingIgnoreCase(String nome, org.springframework.data.domain.Pageable pageable);
}
