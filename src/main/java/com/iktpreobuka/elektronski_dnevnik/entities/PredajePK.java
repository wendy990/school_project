package com.iktpreobuka.elektronski_dnevnik.entities;

import java.io.Serializable;
import java.util.Objects;

public class PredajePK implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer nastavnikId;
	private Integer predmetId;
	private Integer odeljenjeId;

	public PredajePK() {
		super();
	}

	public PredajePK(Integer nastavnikId, Integer predmetId, Integer odeljenjeId) {
		super();
		this.nastavnikId = nastavnikId;
		this.predmetId = predmetId;
		this.odeljenjeId = odeljenjeId;
	}

	public Integer getNastavnikId() {
		return nastavnikId;
	}

	public Integer getPredmetId() {
		return predmetId;
	}

	public Integer getOdeljenjeId() {
		return odeljenjeId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCompositeid() {
		return this.nastavnikId + "_" + this.predmetId + "_" + this.odeljenjeId;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof PredajePK) {
			PredajePK predaje = (PredajePK) object;
			return (predaje.getCompositeid().equals(getCompositeid()));
		}
		return super.equals(object);
	}

	@Override
	public int hashCode() {
		return getCompositeid().hashCode();
	}

}
