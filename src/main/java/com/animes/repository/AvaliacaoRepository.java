package com.animes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.animes.domain.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {

}
