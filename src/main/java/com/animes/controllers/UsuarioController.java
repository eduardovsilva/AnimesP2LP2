package com.animes.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.animes.domain.Usuario;
import com.animes.repository.PapelRepository;
import com.animes.repository.UsuarioRepository;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private PapelRepository papelRepository;
	
	@GetMapping("/usuarios")
	public List<Usuario> getUsuarios() {
		List<Usuario> usuarios = repository.findAll();
		
		return usuarios;
	}

	@GetMapping("/usuarios/{login}")
	public Usuario getUsuario(@PathVariable String login) {
		Usuario usuario = repository.findByLogin(login);
		
		return usuario;
	}
	
	@PostMapping("/usuarios")
	public void saveUsuario(@RequestBody Usuario usuario) {	
		usuario.setPapeis(papelRepository.findByTipoUsuario("ROLE_USUARIO"));
		
		if (repository.findByLogin(usuario.getUsername()) == null) {
			repository.save(usuario);
		}
		
	}
	
	@PutMapping("/usuarios/{login}")
	public void updateUsuario(@RequestBody Usuario usuario, @PathVariable String login, Principal principal) {
		Optional<Usuario> usuarioFound = repository.findById(login);
		
		if (usuarioFound.isPresent() && principal.getName().equals(login)) {
			usuario.setLogin(login);
			repository.save(usuario);
		}
	}
	
	@DeleteMapping("/usuarios/{login}")
	public void deleteUsuario(@PathVariable String login, Principal principal) {
		Optional<Usuario> usuarioFound = repository.findById(login);

		if (usuarioFound.isPresent() && principal.getName().equals(login)) {
			repository.deleteById(login);
		}
		
			
	}
}
