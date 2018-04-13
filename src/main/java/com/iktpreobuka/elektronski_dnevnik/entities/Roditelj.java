package com.iktpreobuka.elektronski_dnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.elektronski_dnevnik.config.Views;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Roditelj extends Osoba {

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(name = "Roditelj_Ucenik", joinColumns = {
			@JoinColumn(name = "roditelj_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "ucenik_id", nullable = false, updatable = false) })
	@JsonView(Views.Admin.class)
	private List<Ucenik> deca = new ArrayList<>();

	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "korisnik")
	@JsonView(Views.Admin.class)
	private Korisnik korisnik;

	public Roditelj() {
		super();
	}

	@JsonManagedReference(value = "roditeljreference")
	public List<Ucenik> getDeca() {
		return deca;
	}

	public void setDeca(List<Ucenik> deca) {
		this.deca = deca;
	}

	@JsonManagedReference(value = "korisnikroditeljreference")
	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

}
