package com.animes.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Admin extends Usuario{

	/*@OneToMany(mappedBy = "admin")
	private List<Anime> animesAdicionados;
	
	@OneToMany(mappedBy = "admin")
	private List<Genero> generosAdicionados;*/
	
	public Admin() {
		super();
	
	}

	public Admin(Integer id, String nome) {
		super(id, nome);
		/*this.animesAdicionados = new ArrayList<Anime>();
		this.generosAdicionados = new ArrayList<Genero>();*/
		
	}

}
