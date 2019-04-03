package com.animes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.animes.domain.AnimeStatus;

public interface AnimeStatusRepository extends JpaRepository<AnimeStatus, Integer>{

}
