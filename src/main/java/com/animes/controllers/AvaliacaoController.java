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

import com.animes.domain.Avaliacao;
import com.animes.domain.Usuario;
import com.animes.repository.AvaliacaoRepository;
import com.animes.repository.UsuarioRepository;

@RestController
public class AvaliacaoController {

	@Autowired
	private AvaliacaoRepository repository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/avaliacoes")
	public ResponseEntity<?> getAvaliacoes() {
		return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
	}

	@PostMapping("/avaliacoes")
	public ResponseEntity<?> saveAvaliacao(@RequestBody Avaliacao avaliacao, Principal principal) {
		List<Avaliacao> avaliacoes = repository.findAll();
		List<String> avaliacoesExistentes = new ArrayList<String>();

		Usuario usuario = usuarioRepository.findByUsername(principal.getName());
		avaliacao.setUsuario(usuario);

		for (Avaliacao a : avaliacoes) {
			avaliacoesExistentes.add(a.getUsuarioAnime());
		}
		if (!avaliacoesExistentes.contains(avaliacao.getUsuarioAnime())) {
			repository.save(avaliacao);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PutMapping("/avaliacoes/{id}")
	public ResponseEntity<?> updateAvaliacao(@RequestBody Avaliacao avaliacao, @PathVariable int id,
			Principal principal) {
		Optional<Avaliacao> avaliacaoFound = repository.findById(id);

		Usuario usuario = usuarioRepository.findByUsername(principal.getName());
		avaliacao.setUsuario(usuario);

		if (avaliacaoFound.isPresent() && principal.getName().equals(avaliacao.getUsuarioNome())
				&& principal.getName().equals(avaliacaoFound.get().getUsuarioNome())) {
			avaliacao.setId(id);
			repository.save(avaliacao);
			return new ResponseEntity<>(HttpStatus.OK);
		} else if (repository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/avaliacoes/{id}")
	public ResponseEntity<?> deleteAvalicacao(@PathVariable int id, Principal principal) {
		Optional<Avaliacao> avaliacaoFound = repository.findById(id);

		if (avaliacaoFound.isPresent() && principal.getName().equals(avaliacaoFound.get().getUsuarioNome())) {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else if (repository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

}
