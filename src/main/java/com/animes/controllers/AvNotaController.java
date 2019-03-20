package com.animes.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.animes.domain.AvNota;
import com.animes.repository.AvNotaRepository;

@RestController
public class AvNotaController {
	
	@Autowired
	private AvNotaRepository repository;
	
	@GetMapping("/notas")
	public List<AvNota> getNotas() {
		List<AvNota> notas = repository.findAll();
		
		return notas;
	}
	
	@GetMapping("/notas/{id}")
	public Optional<AvNota> getNota(@PathVariable int id) {
		Optional<AvNota> nota = repository.findById(id);
		
		return nota;
	}

}
