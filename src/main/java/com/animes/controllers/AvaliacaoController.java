package com.animes.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.animes.domain.Avaliacao;
import com.animes.repository.AvaliacaoRepository;

@RestController
public class AvaliacaoController {
	
	@Autowired
	private AvaliacaoRepository repository;
	
	@GetMapping("/avaliacoes")
	public List<Avaliacao> getAvaliacoes() {
		List<Avaliacao> avaliacoes = repository.findAll();
		
		return avaliacoes;
	}
	
	@PostMapping("/avaliacoes")
	public void saveAvaliacao(@RequestBody Avaliacao avaliacao) {
		repository.save(avaliacao);
	}

}
