package com.animes.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.animes.domain.Anime;
import com.animes.repository.AnimeRepository;

@RestController
public class AnimeController {
	
	@Autowired
	private AnimeRepository repository;

	@GetMapping("/animes")
	public List<Anime> getAllAnimes() {
		List<Anime> animes = repository.findAll();

		return animes;
	}

	@GetMapping("/animes/{id}")
	private Optional<Anime> getAnime(@PathVariable int id) {
		Optional<Anime> animeFound = repository.findById(id);

		return animeFound;
	}
	
	@PostMapping("/animes")
	public ResponseEntity<?> saveAnime(@RequestBody Anime anime) {
		Anime savedAnime = repository.save(anime);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedAnime.getId())
				.toUri();

		return ResponseEntity.created(uri).body("anime added");
	}

	@PutMapping("/animes/{id}")
	public ResponseEntity<?> updateProduct(@RequestBody Anime anime, @PathVariable int id) {
		Optional<Anime> animeFound = repository.findById(id);

		if (!animeFound.isPresent())
			return ResponseEntity.notFound().build();

		anime.setId(id);
		repository.save(anime);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/animes/{id}")
	public void deleteAnime(@PathVariable int id) {
		Optional<Anime> animeFound = repository.findById(id);

		if (animeFound.isPresent())
			repository.deleteById(id);
	}

}
