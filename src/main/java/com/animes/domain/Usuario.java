package com.animes.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, unique = true)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@ManyToMany
	@JoinTable(name = "usuarios_papeis",
				joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
				inverseJoinColumns = @JoinColumn(name = "papel_id", referencedColumnName = "tipoUsuario"))
	private List<Papel> papeis; 
	
	@JsonManagedReference(value = "usuario_avaliacoes")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
	private List<Avaliacao> avaliacoesFeitas;
	
	@JsonManagedReference(value = "usuario_lista")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
	private List<AnimeStatus> listaPessoal;
	
	public Usuario() {}

	public Usuario(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.papeis = new ArrayList<Papel>();
		this.avaliacoesFeitas = new ArrayList<Avaliacao>();
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.papeis;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
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

	public Integer getId() {
		return id;
	}

}
