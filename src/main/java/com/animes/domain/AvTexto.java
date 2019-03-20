package com.animes.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class AvTexto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String corpo;
	
	@JsonBackReference(value = "usuario_textos")
	@ManyToOne(optional = false)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@JsonBackReference(value = "anime_textos")
	@ManyToOne(optional = false)
	@JoinColumn(name = "anime_id")
	private Anime anime;
	
	public AvTexto() {}

	public AvTexto(Usuario usuario, Anime anime, String corpo) {
		super();
		this.usuario = usuario;
		this.anime = anime;
		this.corpo = corpo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCorpo() {
		return corpo;
	}

	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}

	public Integer getUsuarioId() {
		return usuario.getId();
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getAnimeId() {
		return anime.getId();
	}

	public void setAnime(Anime anime) {
		this.anime = anime;
	}
	
}
