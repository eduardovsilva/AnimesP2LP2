package com.animes.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Usuario implements UserDetails{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true)
	private String login;
	
	private String senha;
	
	@ManyToMany
	@JoinTable(name = "usuarios_papeis",
				joinColumns = @JoinColumn(name = "usuario_login", referencedColumnName = "login"),
				inverseJoinColumns = @JoinColumn(name = "papel_id", referencedColumnName = "tipoUsuario"))
	private List<Papel> papeis; 
	
	@JsonManagedReference(value = "usuario_avaliacoes")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
	private List<Avaliacao> avaliacoesFeitas;
	
	@JsonManagedReference(value = "usuario_lista")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
	private List<AnimeStatus> listaPessoal;
	
	public Usuario() {}

	public Usuario(String login, String senha) {
		super();
		this.login = login;
		this.senha = senha;
		this.papeis = new ArrayList<Papel>();
		this.avaliacoesFeitas = new ArrayList<Avaliacao>();
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.papeis;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public void setSenha(String senha) {
		this.senha = new BCryptPasswordEncoder().encode(senha);
	}
	
	public void setPapeis(List<Papel> papeis) {
		this.papeis = papeis;
	}

	public List<Avaliacao> getAvaliacoesFeitas() {
		return avaliacoesFeitas;
	}

	public List<AnimeStatus> getListaPessoal() {
		return listaPessoal;
	}
	
}
