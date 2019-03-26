package com.animes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.animes.domain.Papel;

public interface PapelRepository extends JpaRepository<Papel, String> {
	
	List<Papel> findByTipoUsuario(String tipoUsuario);

}
