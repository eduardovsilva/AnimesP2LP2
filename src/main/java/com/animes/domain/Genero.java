package com.animes.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Genero {
	
	@Id
	@Column(unique = true)
	private String nome;
	
	@ManyToMany(fetch = FetchType.EAGER,
            	cascade = {
            			CascadeType.PERSIST,
            			CascadeType.MERGE
                }, mappedBy = "generos")
	private List<Anime> animes;
	
	public Genero() {}

	public Genero( String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
