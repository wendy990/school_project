package com.iktpreobuka.elektronski_dnevnik.entities.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iktpreobuka.elektronski_dnevnik.enums.TipKorisnika;

public class OsobaDTO {

	private TipKorisnika tipKorisnika;
	private String ime;
	private String prezime;
	private String email;
	private String jmbg;
	private Date datumRodjenja;
	private String adresa;

	public OsobaDTO() {
		super();
	}

	public OsobaDTO(TipKorisnika tipKorisnika, String ime, String prezime, String email, String jmbg,
			Date datumRodjenja, String adresa) {
		super();
		this.tipKorisnika = tipKorisnika;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.jmbg = jmbg;
		this.datumRodjenja = datumRodjenja;
		this.adresa = adresa;
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

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

}
