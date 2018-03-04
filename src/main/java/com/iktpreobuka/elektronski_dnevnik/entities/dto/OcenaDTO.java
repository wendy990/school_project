package com.iktpreobuka.elektronski_dnevnik.entities.dto;

import java.util.Date;

import com.iktpreobuka.elektronski_dnevnik.enums.TipOcene;

public class OcenaDTO {

	private TipOcene tipOcene;
	private Integer vrednost;
	private Date datumUnosa;
	private Integer polugodiste;
	private boolean zakljucna;
	private boolean deleted;

	public OcenaDTO() {
		super();
	}

	public OcenaDTO(TipOcene tipOcene, Integer vrednost, Date datumUnosa, Integer polugodiste, boolean zakljucna,
			boolean deleted) {
		super();
		this.tipOcene = tipOcene;
		this.vrednost = vrednost;
		this.datumUnosa = datumUnosa;
		this.polugodiste = polugodiste;
		this.zakljucna = zakljucna;
		this.deleted = deleted;
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

}
