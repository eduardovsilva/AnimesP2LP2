package com.animes.controllers;

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
import com.animes.domain.Anime;
import com.animes.repository.AnimeRepository;

@RestController
public class AnimeController {
	
	@Autowired
	private AnimeRepository repository;

	@GetMapping("/animes")
	public ResponseEntity<?> getAnimes() {
		return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
	}

	@GetMapping("/animes/{id}")
	private ResponseEntity<?> getAnime(@PathVariable int id) {
		Optional<Anime> animeFound = repository.findById(id);

		if (animeFound.isPresent()) {
			return new ResponseEntity<>(animeFound.get(), HttpStatus.FOUND);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/animes")
	public ResponseEntity<?> saveAnime(@RequestBody Anime anime) {
		 repository.save(anime);
		 return new ResponseEntity<>(HttpStatus.CREATED);

	}
	
	@PutMapping("/animes/{id}")
	public ResponseEntity<?> updateAnime(@RequestBody Anime anime, @PathVariable int id) {
		Optional<Anime> animeFound = repository.findById(id);

		if (animeFound.isPresent()) {
			anime.setId(id);
			repository.save(anime);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/animes/{id}")
	public ResponseEntity<?> deleteAnime(@PathVariable int id) {
		Optional<Anime> animeFound = repository.findById(id);

		if (animeFound.isPresent()) {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
			
	}

}
