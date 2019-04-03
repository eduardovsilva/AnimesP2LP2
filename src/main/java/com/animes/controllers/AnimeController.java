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
	public void saveAnime(@RequestBody Anime anime) {
		 repository.save(anime);

	}
	
	@PutMapping("/animes/{id}")
	public void updateAnime(@RequestBody Anime anime, @PathVariable int id) {
		Optional<Anime> animeFound = repository.findById(id);

		if (animeFound.isPresent()) {
			anime.setId(id);
			repository.save(anime);
		}	
	}

	@DeleteMapping("/animes/{id}")
	public void deleteAnime(@PathVariable int id) {
		Optional<Anime> animeFound = repository.findById(id);

		if (animeFound.isPresent())
			repository.deleteById(id);
	}

}
