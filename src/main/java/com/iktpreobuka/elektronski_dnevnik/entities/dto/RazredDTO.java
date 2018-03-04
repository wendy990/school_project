package com.iktpreobuka.elektronski_dnevnik.entities.dto;

public class RazredDTO {

	private Integer razred;
	private String oznaka;

	public RazredDTO() {
		super();
	}

	public RazredDTO(Integer razred, String oznaka) {
		super();
		this.razred = razred;
		this.oznaka = oznaka;
	}

	public Integer getRazred() {
		return razred;
	}

	public void setRazred(Integer razred) {
		this.razred = razred;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

}
