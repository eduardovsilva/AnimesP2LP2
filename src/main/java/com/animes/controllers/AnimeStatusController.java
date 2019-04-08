package com.animes.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
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

import com.animes.domain.AnimeStatus;
import com.animes.domain.Usuario;
import com.animes.repository.AnimeStatusRepository;
import com.animes.repository.UsuarioRepository;

@RestController
public class AnimeStatusController {

	@Autowired
	private AnimeStatusRepository repository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/lista")
	public ResponseEntity<?> getListas() {
		return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
	}

	@PostMapping("/lista")
	public ResponseEntity<?> saveStatus(@RequestBody AnimeStatus status, Principal principal) {
		List<AnimeStatus> listas = repository.findAll();
		List<String> statusExistentes = new ArrayList<String>();

		Usuario usuario = usuarioRepository.findByUsername(principal.getName());
		status.setUsuario(usuario);

		for (AnimeStatus a : listas) {
			statusExistentes.add(a.getUsuarioAnime());
		}

		if (!statusExistentes.contains(status.getUsuarioAnime())) {
			repository.save(status);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PutMapping("/lista/{id}")
	public ResponseEntity<?> updateStatus(@RequestBody AnimeStatus status, @PathVariable int id, Principal principal) {
		Optional<AnimeStatus> statusFound = repository.findById(id);

		Usuario usuario = usuarioRepository.findByUsername(principal.getName());
		status.setUsuario(usuario);

		if (statusFound.isPresent() && principal.getName().equals(status.getUsuarioNome())
				&& principal.getName().equals(statusFound.get().getUsuarioNome())) {
			status.setId(id);
			repository.save(status);
			return new ResponseEntity<>(HttpStatus.OK);
		} else if (repository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/lista/{id}")
	public ResponseEntity<?> deleteStatus(@PathVariable int id, Principal principal) {
		Optional<AnimeStatus> statusFound = repository.findById(id);

		if (statusFound.isPresent() && principal.getName().equals(statusFound.get().getUsuarioNome())) {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else if (repository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

}
