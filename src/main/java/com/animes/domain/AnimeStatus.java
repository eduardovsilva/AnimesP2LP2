package com.animes.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnimeStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonBackReference(value = "usuario_lista")
	@ManyToOne(optional = false)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@JsonBackReference(value = "anime_lista")
	@ManyToOne(optional = false)
	@JoinColumn(name = "anime_id")
	private Anime anime;
	
	private String status;

	public AnimeStatus() {}
	
	public AnimeStatus(Integer id, Usuario usuario, Anime anime, String status) {
		this.usuario = usuario;
		this.anime = anime;
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setAnime(Anime anime) {
		this.anime = anime;
	}
	
	public String getAnimeNome() {
		return anime.getNome();
	}
		
	public String getUsuarioNome() {
		return usuario.getUsername();
	}

	public Integer getAnimeId() {
		return anime.getId();
	}
	
	public Integer getUsuarioId() {
		return usuario.getId();
	}
	
	@JsonIgnore
	public String getUsuarioAnime() {
		return this.getUsuarioId() + "" + this.getAnimeId();
	}

	public Integer getId() {
		return id;
	}
	
}
