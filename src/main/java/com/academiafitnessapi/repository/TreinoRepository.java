package com.academiafitnessapi.repository;

import com.academiafitnessapi.model.Treino;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreinoRepository extends JpaRepository<Treino, Long> {
}
