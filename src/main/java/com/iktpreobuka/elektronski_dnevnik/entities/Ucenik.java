package com.iktpreobuka.elektronski_dnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.elektronski_dnevnik.config.Views;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Ucenik extends Osoba {

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "odeljenje")
	private Odeljenje odeljenje;
	@OneToMany(mappedBy = "ucenik", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<Ocena> ocene = new ArrayList<>();
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(name = "Roditelj_Ucenik", joinColumns = {
			@JoinColumn(name = "ucenik_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "roditelj_id", nullable = false, updatable = false) })
	private List<Roditelj> roditelji = new ArrayList<>();
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "korisnik")
	@JsonView(Views.Admin.class)
	private Korisnik korisnik;

	public Ucenik() {
		super();
	}

	public Ucenik(Odeljenje odeljenje, List<Ocena> ocene, List<Roditelj> roditelji, Korisnik korisnik) {
		super();
		this.odeljenje = odeljenje;
		this.ocene = ocene;
		this.roditelji = roditelji;
		this.korisnik = korisnik;
	}

	@JsonManagedReference(value = "ucenikreference")
	public Odeljenje getOdeljenje() {
		return odeljenje;
	}

	public void setOdeljenje(Odeljenje odeljenje) {
		this.odeljenje = odeljenje;
	}

	@JsonBackReference(value = "ocenaucenikreference")
	public List<Ocena> getOcene() {
		return ocene;
	}

	public void setOcene(List<Ocena> ocene) {
		this.ocene = ocene;
	}

	@JsonBackReference(value = "roditeljreference")
	public List<Roditelj> getRoditelji() {
		return roditelji;
	}

	public void setRoditelji(List<Roditelj> roditelji) {
		this.roditelji = roditelji;
	}

	@JsonManagedReference(value = "korisnikucenikreference")
	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

}
