package com.animes.controllers;

import java.security.Principal;
import java.util.ArrayList;
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
		List<Avaliacao> avaliacoes = repository.findAll();
		List<String> avaliacoesExistentes = new ArrayList<String>();
		
		for (Avaliacao a: avaliacoes) {
			avaliacoesExistentes.add(a.getUsuarioAnime());		
		}
			
		if (!avaliacoesExistentes.contains(avaliacao.getUsuarioAnime())) {
			repository.save(avaliacao);
		}
	}

	@PutMapping("/avaliacoes/{id}")
	public void updateAvaliacao(@RequestBody Avaliacao avaliacao, @PathVariable int id, Principal principal) {
		Optional<Avaliacao> avaliacaoFound = repository.findById(id);

		if (avaliacaoFound.isPresent() && principal.getName().equals(avaliacaoFound.get().getUsuarioNome())) {
			avaliacao.setId(id);
			repository.save(avaliacao);
		}
	}

	@DeleteMapping("/avaliacoes/{id}")
	public void deleteAvalicacao(@PathVariable int id, Principal principal) {
		Optional<Avaliacao> avaliacaoFound = repository.findById(id);
		
		if (avaliacaoFound.isPresent() && principal.getName().equals(avaliacaoFound.get().getUsuarioNome()))
			repository.deleteById(id);
	}
}
