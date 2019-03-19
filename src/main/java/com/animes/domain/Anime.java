package com.animes.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Anime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	private int episodios;
	
	private String classificacao;
	
	@ManyToMany
	@JoinTable(name = "anime_genero",
	        joinColumns = @JoinColumn(name = "anime_id", referencedColumnName = "id"),
	        inverseJoinColumns = @JoinColumn(name = "genero_id", referencedColumnName = "id"))
	private List<Genero> generos;
	
	@OneToMany(mappedBy = "anime")
	private List<AvNota> avaliacoesNota;
	
	@OneToMany(mappedBy = "anime")
	private List<AvTexto> avaliacoesTexto;
	
	@ManyToOne
	@JoinColumn(name = "admin_id")
	private Admin admin;

	public Anime() {}
	
	public Anime(Integer id, String nome, int episodios, String classificacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.episodios = episodios;
		this.classificacao = classificacao;
		this.generos =  new ArrayList<Genero>();
		this.avaliacoesNota = new ArrayList<AvNota>();
		this.avaliacoesTexto = new ArrayList<AvTexto>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getEpisodios() {
		return episodios;
	}

	public void setEpisodios(int episodios) {
		this.episodios = episodios;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}
	
}

