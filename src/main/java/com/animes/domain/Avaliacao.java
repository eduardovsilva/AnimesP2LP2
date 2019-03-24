package com.animes.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Avaliacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private int nota;
	
	private String texto;
	
	@JsonBackReference(value = "usuario_avaliacoes")
	@ManyToOne(optional = false)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@JsonBackReference(value = "anime_avaliacoes")
	@ManyToOne(optional = false)
	@JoinColumn(name = "anime_id")
	private Anime anime;
	
	public Avaliacao() {}

	public Avaliacao(Usuario usuario, Anime anime, int nota, String texto) {
		super();
		this.usuario = usuario;
		this.anime = anime;
		if (nota < 0 || nota > 10) {
			throw new IllegalArgumentException("A nota precisa estar entre 0 e 10");
		} else {
			this.nota = nota;
		}
		this.texto = texto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		if (nota < 0 || nota > 10) {
			throw new IllegalArgumentException("A nota precisa estar entre 0 e 10");
		} else {
			this.nota = nota;
		}
	}

	public String getUsuarioNome() {
		return usuario.getUsername();
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getAnimeId() {
		return anime.getId();
	}
	
	public String getAnimeNome() {
		return anime.getNome();
	}

	public void setAnime(Anime anime) {
		this.anime = anime;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}
