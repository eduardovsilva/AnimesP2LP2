package com.animes.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.animes.domain.AvTexto;
import com.animes.repository.AvTextoRepository;

@RestController
public class AvTextoController {

	@Autowired
	private AvTextoRepository repository;
	
	@GetMapping("/textos")
	public List<AvTexto> getTextos() {
		List<AvTexto> textos = repository.findAll();
		
		return textos;
	}
	
	@GetMapping("/textos/{id}")
	public Optional<AvTexto> getTexto(@PathVariable int id) {
		Optional<AvTexto> texto = repository.findById(id);
		
		return texto;
	}
	
	@PostMapping("/textos")
	public void saveTexto(@RequestBody AvTexto texto) {
		repository.save(texto);
	}
}
