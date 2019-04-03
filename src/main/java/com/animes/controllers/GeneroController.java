package com.animes.controllers;

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

import com.animes.domain.Genero;
import com.animes.repository.GeneroRepository;

@RestController
public class GeneroController {

	@Autowired
	private GeneroRepository repository;
	
	@GetMapping("/generos")
	public List<Genero> getGeneros() {
		List<Genero> generos = repository.findAll();
		
		return generos;
	}
	
	@PostMapping("/generos")
	public void saveGenero(@RequestBody Genero genero) {
		repository.save(genero);
	}
	
	@PutMapping("/generos/{id}")
	public void updateGenero(@RequestBody Genero genero, @PathVariable int id) {
		Optional<Genero> generoFound = repository.findById(id);

		if (generoFound.isPresent()) {
			genero.setId(id);
			repository.save(genero);
		}
	}
	
	@DeleteMapping("/generos/{id}")
	public void deleteGenero(@PathVariable int id) {
		Optional<Genero> generoFound = repository.findById(id);

		if (generoFound.isPresent())
			repository.deleteById(id);
	}
}
