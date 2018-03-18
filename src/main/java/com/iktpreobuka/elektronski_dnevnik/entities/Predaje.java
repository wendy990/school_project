package com.iktpreobuka.elektronski_dnevnik.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(PredajePK.class)
public class Predaje {

	@Id
	@Column(nullable = false, insertable = false, updatable = false)
	private Integer nastavnikId;

	@Id
	@Column(nullable = false, insertable = false, updatable = false)
	private Integer predmetId;
	
	@Id
	@Column(nullable = false, insertable = false, updatable = false)
	private Integer odeljenjeId;
	
	

	public Predaje() {
		super();
	}

	public Predaje(Integer nastavnikId, Integer predmetId, Integer odeljenjeId) {
		super();
		this.nastavnikId = nastavnikId;
		this.predmetId = predmetId;
		this.odeljenjeId = odeljenjeId;
	}

	public Integer getNastavnikId() {
		return nastavnikId;
	}

	public void setNastavnikId(Integer nastavnikId) {
		this.nastavnikId = nastavnikId;
	}

	public Integer getPredmetId() {
		return predmetId;
	}

	public void setPredmetId(Integer predmetId) {
		this.predmetId = predmetId;
	}

	public Integer getOdeljenjeId() {
		return odeljenjeId;
	}

	public void setOdeljenjeId(Integer odeljenjeId) {
		this.odeljenjeId = odeljenjeId;
	}
	
	

}
