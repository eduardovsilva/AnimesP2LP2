package com.animes.controllers;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> getUsuarios() {
		return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
	}

	@GetMapping("/usuarios/{username}")
	public ResponseEntity<?> getUsuario(@PathVariable String username) {
		Usuario usuario = repository.findByUsername(username);

		if (usuario != null) {
			return new ResponseEntity<>(usuario, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/usuarios")
	public ResponseEntity<?> saveUsuario(@RequestBody Usuario usuario) {
		usuario.setPapeis(papelRepository.findByTipoUsuario("ROLE_USUARIO"));

		if (repository.findByUsername(usuario.getUsername()) == null) {
			repository.save(usuario);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PutMapping("/usuarios/{id}")
	public ResponseEntity<?> updateUsuario(@RequestBody Usuario usuario, @PathVariable int id, Principal principal) {
		Optional<Usuario> usuarioFound = repository.findById(id);

		if (usuarioFound.isPresent() && principal.getName().equals(usuarioFound.get().getUsername())) {
			usuario.setId(id);
			repository.save(usuario);
			return new ResponseEntity<>(HttpStatus.OK);
		} else if (repository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<?> deleteUsuario(@PathVariable int id, Principal principal) {
		Optional<Usuario> usuarioFound = repository.findById(id);

		if (usuarioFound.isPresent() && principal.getName().equals(usuarioFound.get().getUsername())) {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else if (repository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
}
