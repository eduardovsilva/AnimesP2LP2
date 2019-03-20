package com.animes.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class AvNota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private int valor;
	
	@JsonBackReference(value = "usuario_notas")
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@JsonBackReference(value = "anime_notas")
	@ManyToOne
	@JoinColumn(name = "anime_id")
	private Anime anime;
	
	public AvNota() {}

	public AvNota(Usuario usuario, Anime anime, int valor) {
		super();
		this.usuario = usuario;
		this.anime = anime;
		this.valor = valor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
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
