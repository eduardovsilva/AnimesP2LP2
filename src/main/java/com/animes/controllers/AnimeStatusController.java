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

import com.animes.domain.AnimeStatus;
import com.animes.repository.AnimeStatusRepository;

@RestController
public class AnimeStatusController {

	@Autowired
	private AnimeStatusRepository repository;
	
	@GetMapping("/lista")
	public List<AnimeStatus> getListas() {
		List<AnimeStatus> listas = repository.findAll();
		
		return listas;
	}
	
	@PostMapping("/lista")
	public void saveStatus(@RequestBody AnimeStatus status) {
		List<AnimeStatus> listas = repository.findAll();
		List<String> statusExistentes = new ArrayList<String>();
		
		for (AnimeStatus a: listas) {
			statusExistentes.add(a.getUsuarioAnime());		
		}
			
		if (!statusExistentes.contains(status.getUsuarioAnime())) {	
			repository.save(status);
		}
	}

	@PutMapping("/lista/{id}")
	public void updateStatus(@RequestBody AnimeStatus status, @PathVariable int id, Principal principal) {
		Optional<AnimeStatus> statusFound = repository.findById(id);

		if (statusFound.isPresent() && principal.getName() == statusFound.get().getUsuarioNome()) {
			status.setId(id);
			repository.save(status);
		}
	}
	
	@DeleteMapping("/lista/{id}")
	public void deleteStatus(@PathVariable int id, Principal principal) {
		Optional<AnimeStatus> statusFound = repository.findById(id);

		if (statusFound.isPresent() && principal.getName() == statusFound.get().getUsuarioNome())
			repository.deleteById(id);
	}
	
}
