package com.animes.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;
	private String tipo_usuario;
	
	@JsonManagedReference(value = "usuario_notas")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
	private List<AvNota> avaliacoesFeitasNota;
	
	@JsonManagedReference(value = "usuario_textos")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
	private List<AvTexto> avaliacoesFeitasTexto;
	
	public Usuario() {}

	public Usuario(String nome) {
		super();
		this.nome = nome;
		this.avaliacoesFeitasNota = new ArrayList<AvNota>();
		this.avaliacoesFeitasTexto = new ArrayList<AvTexto>();
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
	
	public String getTipo() {
		return tipo_usuario;
	}
	
}
