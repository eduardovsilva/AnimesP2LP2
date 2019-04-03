package com.animes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.animes.domain.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	
	Usuario findByLogin(String login);
}
