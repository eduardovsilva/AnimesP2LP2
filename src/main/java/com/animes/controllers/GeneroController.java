package com.animes.controllers;

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

import com.animes.domain.Genero;
import com.animes.repository.GeneroRepository;

@RestController
public class GeneroController {

	@Autowired
	private GeneroRepository repository;

	@GetMapping("/generos")
	public ResponseEntity<?> getGeneros() {
		return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
	}

	@PostMapping("/generos")
	public ResponseEntity<?> saveGenero(@RequestBody Genero genero) {

		if (repository.findByNome(genero.getNome()) != null) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		} else {
			repository.save(genero);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
	}

	@PutMapping("/generos/{id}")
	public ResponseEntity<?> updateGenero(@RequestBody Genero genero, @PathVariable int id) {
		Optional<Genero> generoFound = repository.findById(id);

		if (generoFound.isPresent()) {
			genero.setId(id);
			repository.save(genero);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/generos/{id}")
	public ResponseEntity<?> deleteGenero(@PathVariable int id) {
		Optional<Genero> generoFound = repository.findById(id);

		if (generoFound.isPresent()) {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
}
