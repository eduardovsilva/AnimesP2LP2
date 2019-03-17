package com.animes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.animes.domain.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

}
