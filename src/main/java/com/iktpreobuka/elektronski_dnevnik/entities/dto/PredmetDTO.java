package com.iktpreobuka.elektronski_dnevnik.entities.dto;

public class PredmetDTO {

	private String naziv;
	private Integer fond;

	public PredmetDTO() {
		super();
	}

	public PredmetDTO(String naziv, Integer fond) {
		super();
		this.naziv = naziv;
		this.fond = fond;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Integer getFond() {
		return fond;
	}

	public void setFond(Integer fond) {
		this.fond = fond;
	}

}
