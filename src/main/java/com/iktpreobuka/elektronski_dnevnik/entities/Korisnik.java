package com.iktpreobuka.elektronski_dnevnik.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Korisnik {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "email")
	// @ValidEmail(message = "*Please provide an email")
	// @NotEmpty(message = "*Please provide an email")
	private String email;
	@Column(name = "password")
	// @Length(min = 5, message = "*Your password must have at least 5 characters")
	// @NotEmpty(message = "*Please provide your password")
	// @Transient
	private String password;
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "role")
	private Role role;
	@OneToOne(mappedBy = "korisnik", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private Ucenik ucenik;
	@OneToOne(mappedBy = "korisnik", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private Nastavnik nastavnik;
	@OneToOne(mappedBy = "korisnik", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private Roditelj roditelj;
	@OneToOne(mappedBy = "korisnik", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private Administrator administrator;
	// ILI DA BUDE OSOBA OSOBA?
	@Version
	private Integer version;

	public Korisnik() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@JsonManagedReference(value = "korisnikrolereference")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@JsonBackReference(value = "korisnikucenikreference")
	public Ucenik getUcenik() {
		return ucenik;
	}

	public void setUcenik(Ucenik ucenik) {
		this.ucenik = ucenik;
	}

	@JsonBackReference(value = "korisniknastavnikreference")
	public Nastavnik getNastavnik() {
		return nastavnik;
	}

	public void setNastavnik(Nastavnik nastavnik) {
		this.nastavnik = nastavnik;
	}
	
	@JsonBackReference(value = "korisnikroditeljreference")
	public Roditelj getRoditelj() {
		return roditelj;
	}

	public void setRoditelj(Roditelj roditelj) {
		this.roditelj = roditelj;
	}
	
	@JsonBackReference(value = "korisnikadministratorreference")
	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

}