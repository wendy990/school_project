package com.iktpreobuka.elektronski_dnevnik.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.iktpreobuka.elektronski_dnevnik.customdate.CustomDateDeserializer;
import com.iktpreobuka.elektronski_dnevnik.customdate.CustomDateSerializer;
import com.iktpreobuka.elektronski_dnevnik.enums.TipOcene;

@Entity
@Where(clause = "deleted = false")
@SQLDelete(sql="UPDATE ocena SET deleted = 'true' where id=?")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Ocena {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "Tip_ocene")
	private TipOcene tipOcene;
	@Column(name = "Vrednost")
	private Integer vrednost;
	@Column(name = "Datum_unosa")
	private Date datumUnosa;
	@Column(name = "Polugodiste")
	private Integer polugodiste;
	@Column(name = "Zakljucna")
	private boolean zakljucna;
	@Column
	private boolean deleted;
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "nastavnik")
	private Nastavnik nastavnik;
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "ucenik")
	private Ucenik ucenik;
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "predmet")
	private Predmet predmet;
	@Version
	private Integer version;

	public Ocena() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipOcene getTipOcene() {
		return tipOcene;
	}

	public void setTipOcene(TipOcene tipOcene) {
		this.tipOcene = tipOcene;
	}

	public Integer getVrednost() {
		return vrednost;
	}

	public void setVrednost(Integer vrednost) {
		this.vrednost = vrednost;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	@JsonDeserialize(using = CustomDateDeserializer.class)
	public Date getDatumUnosa() {
		return datumUnosa;
	}

	public void setDatumUnosa(Date datumUnosa) {
		this.datumUnosa = datumUnosa;
	}

	public Integer getPolugodiste() {
		return polugodiste;
	}

	public void setPolugodiste(Integer polugodiste) {
		this.polugodiste = polugodiste;
	}

	public boolean isZakljucna() {
		return zakljucna;
	}

	public void setZakljucna(boolean zakljucna) {
		this.zakljucna = zakljucna;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@JsonManagedReference(value = "nastavnikocenareference")
	public Nastavnik getNastavnik() {
		return nastavnik;
	}

	public void setNastavnik(Nastavnik nastavnik) {
		this.nastavnik = nastavnik;
	}

	@JsonManagedReference(value = "ocenaucenikreference")
	public Ucenik getUcenik() {
		return ucenik;
	}

	public void setUcenik(Ucenik ucenik) {
		this.ucenik = ucenik;
	}

	@JsonManagedReference(value = "ocenapredmetreference")
	public Predmet getPredmet() {
		return predmet;
	}

	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
