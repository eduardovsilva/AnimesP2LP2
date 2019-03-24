package com.animes.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Papel implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	private String tipoUsuario;
	
	@ManyToMany(fetch = FetchType.EAGER,
        	cascade = {
        			CascadeType.PERSIST,
        			CascadeType.MERGE
            }, mappedBy = "papeis")
	private List<Usuario> usuarios;
	
	public Papel() {}
	
	public Papel(String tipoUsuario) {
		super();
		this.tipoUsuario = tipoUsuario;
		this.usuarios = new ArrayList<Usuario>();
	}

	@JsonIgnore
	@Override
	public String getAuthority() {
		return this.tipoUsuario;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}
