package com.iktpreobuka.elektronski_dnevnik.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.elektronski_dnevnik.entities.Korisnik;
import com.iktpreobuka.elektronski_dnevnik.repositories.KorisnikRepository;

@RestController
@RequestMapping(path = "/api/v1/korisnik")
public class KorisnikController {

	// procitaj ga iz baze po username(email)
	// procitaj ga iz baze po id
	// obrisi korisnika

	@Autowired
	private KorisnikRepository korisnikRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getKorisnikById(@PathVariable Integer id) {
		try {
			if (korisnikRepository.exists(id)) {
				Korisnik korisnik = korisnikRepository.findOne(id);
				return new ResponseEntity<Korisnik>(korisnik, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(1, "Korisnik ne postoji u bazi"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/username")
	public ResponseEntity<?> getKorisnikByUsername(@RequestParam String email) {
		try {
			if (korisnikRepository.findByEmail(email) != null) {
				Korisnik korisnik = korisnikRepository.findByEmail(email);
				return new ResponseEntity<Korisnik>(korisnik, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(3, "Korisnik ne postoji u bazi"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(4, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteKorisnik(@PathVariable Integer id) {
		try {
			if (korisnikRepository.exists(id)) {
				Korisnik korisnik = korisnikRepository.findOne(id);
				korisnikRepository.delete(korisnik);
				return new ResponseEntity<Korisnik>(korisnik, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(3, "Korisnik ne postoji u bazi"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(4, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}