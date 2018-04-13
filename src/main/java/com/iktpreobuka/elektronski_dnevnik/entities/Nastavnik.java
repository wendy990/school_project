package com.iktpreobuka.elektronski_dnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.elektronski_dnevnik.config.Views;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Nastavnik extends Osoba {

	@OneToMany(mappedBy = "nastavnik", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<Ocena> ocene = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "korisnik")
	@JsonView(Views.Admin.class)
	private Korisnik korisnik;

	public Nastavnik() {
		super();
	}

	@JsonBackReference(value = "nastavnikocenareference")
	public List<Ocena> getOcene() {
		return ocene;
	}

	public void setOcene(List<Ocena> ocene) {
		this.ocene = ocene;
	}

	@JsonManagedReference(value = "korisniknastavnikreference")
	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	
	

}
