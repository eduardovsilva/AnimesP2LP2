package com.animes.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PreRemove;

@Entity
public class Genero {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, unique = true)
	private String nome;
	
	@ManyToMany(fetch = FetchType.EAGER,
            	cascade = {
            			CascadeType.PERSIST,
            			CascadeType.MERGE,
            			CascadeType.REFRESH
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
	
	@PreRemove
	private void removeGenerosFromAnimes() {
	    for (Anime a : animes) {
	        a.getGeneros().remove(this);
	    }
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
