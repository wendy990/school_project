package com.iktpreobuka.elektronski_dnevnik.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.elektronski_dnevnik.repositories.KorisnikRepository;

@RestController
@RequestMapping(path = "/api/v1/korisnik")
public class KorisnikController {
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	//kreiraj korisnika CREATE
	//procitaj ga iz baze po username GETBYUSERNAME
	//izmeni podatke o korisniku MODIFY
	//obrisi korisnika DELETE

}
