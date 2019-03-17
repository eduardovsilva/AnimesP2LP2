package com.animes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.animes.domain.Anime;

public interface AnimeRepository extends JpaRepository<Anime, Integer> {

}
