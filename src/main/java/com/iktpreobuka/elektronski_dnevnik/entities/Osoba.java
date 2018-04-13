package com.iktpreobuka.elektronski_dnevnik.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.iktpreobuka.elektronski_dnevnik.config.Views;
import com.iktpreobuka.elektronski_dnevnik.customdate.CustomDateDeserializer;
import com.iktpreobuka.elektronski_dnevnik.customdate.CustomDateSerializer;
import com.iktpreobuka.elektronski_dnevnik.enums.TipKorisnika;

@MappedSuperclass
public abstract class Osoba {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.Public.class)
	protected Integer id;
	@Column(name = "tip_korisnika")
	@JsonView(Views.Admin.class)
	protected TipKorisnika tipKorisnika;
	@Column(name = "ime")
	@JsonView(Views.Public.class)
	protected String ime;
	@Column(name = "prezime")
	@JsonView(Views.Public.class)
	protected String prezime;
	@Column(name = "email", unique = true)
	@JsonView(Views.Admin.class)
	protected String email;
	@Column(name = "jmbg", length = 13, unique = true)
	@JsonView(Views.Admin.class)
	protected String jmbg;
	@Column(name = "datum_rodjenja")
	@JsonView(Views.Admin.class)
	protected Date datumRodjenja;
	@Column(name = "adresa")
	@JsonView(Views.Admin.class)
	protected String adresa;
	//@OneToOne
	//private Korisnik korisnik;
	@Version
	protected Integer version;

	public Osoba() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipKorisnika getTipKorisnika() {
		return tipKorisnika;
	}

	public void setTipKorisnika(TipKorisnika tipKorisnika) {
		this.tipKorisnika = tipKorisnika;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}
	
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getDatumRodjenja() {
		return datumRodjenja;
	}
	
	@JsonDeserialize(using = CustomDateDeserializer.class)
	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
