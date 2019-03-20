package com.animes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
}
