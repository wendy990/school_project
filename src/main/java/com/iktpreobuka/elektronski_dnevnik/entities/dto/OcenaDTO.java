package com.iktpreobuka.elektronski_dnevnik.entities.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iktpreobuka.elektronski_dnevnik.enums.TipOcene;

public class OcenaDTO {

	private TipOcene tipOcene;
	private Integer vrednost;
	private Integer polugodiste;
	//private Date datumUnosa;
	private boolean deleted;

	public OcenaDTO() {
		super();
	}

	public OcenaDTO(TipOcene tipOcene, Integer vrednost, Integer polugodiste,
			boolean deleted) {
		super();
		this.tipOcene = tipOcene;
		this.vrednost = vrednost;
		this.polugodiste = polugodiste;
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

	public Integer getPolugodiste() {
		return polugodiste;
	}

	public void setPolugodiste(Integer polugodiste) {
		this.polugodiste = polugodiste;
	}
	
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
