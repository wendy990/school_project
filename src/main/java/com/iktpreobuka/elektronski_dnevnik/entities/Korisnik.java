package com.iktpreobuka.elektronski_dnevnik.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Korisnik{

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "korisnik_id")
    private int id;
	@Column(name = "email")
    //@ValidEmail(message = "*Please provide an email")
    //@NotEmpty(message = "*Please provide an email")
	private String email;
	@Column(name = "password")
    //@Length(min = 5, message = "*Your password must have at least 5 characters")
    //@NotEmpty(message = "*Please provide your password")
    //@Transient
	private String password;
	
	//OneToOne(mappedBy = "korisnik", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	//private Ucenik ucenikId;
	
	//OneToOne(mappedBy = "korisnik", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	//private Nastavnik nastavnikId;
	
	//OneToOne(mappedBy = "korisnik", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    //private Roditelj roditeljId;
	
	//ILI DA BUDE OSOBA OSOBA?

	
	/*@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "role")
	private RoleEntity role;*/
	
	@Version
	private Integer version;
	
	public Korisnik(String email, String password, Integer version) {
		super();
		this.email = email;
		this.password = password;
		this.version = version;
	}
	
	public Korisnik() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	
}