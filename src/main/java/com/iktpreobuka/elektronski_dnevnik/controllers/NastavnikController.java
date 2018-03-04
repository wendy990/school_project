package com.iktpreobuka.elektronski_dnevnik.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.elektronski_dnevnik.entities.Nastavnik;
import com.iktpreobuka.elektronski_dnevnik.entities.Predmet;
import com.iktpreobuka.elektronski_dnevnik.entities.dto.OsobaDTO;
import com.iktpreobuka.elektronski_dnevnik.enums.TipKorisnika;
import com.iktpreobuka.elektronski_dnevnik.repositories.NastavnikRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.PredmetRepository;

@RestController
@RequestMapping(path = "/api/v1/nastavnik")
public class NastavnikController {

	@Autowired
	private NastavnikRepository nastavnikRepository;
	
	@Autowired
	private PredmetRepository predmetRepository;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createNastavnik(@RequestBody OsobaDTO nastavnikDTO) {
		if (nastavnikDTO.getTipKorisnika().equals(TipKorisnika.NASTAVNIK)) {
			if (nastavnikRepository.findByJmbg(nastavnikDTO.getJmbg()) == null) {
				Nastavnik nastavnik = new Nastavnik();
				nastavnik.setTipKorisnika(nastavnikDTO.getTipKorisnika());
				nastavnik.setIme(nastavnikDTO.getIme());
				nastavnik.setPrezime(nastavnikDTO.getPrezime());
				nastavnik.setJmbg(nastavnikDTO.getJmbg());
				nastavnik.setDatumRodjenja(nastavnikDTO.getDatumRodjenja());
				nastavnik.setEmail(nastavnikDTO.getEmail());
				nastavnik.setAdresa(nastavnikDTO.getAdresa());
				nastavnikRepository.save(nastavnik);
				return new ResponseEntity<Nastavnik>(nastavnik, HttpStatus.OK);
			} else {
				return new ResponseEntity<RESTError>(new RESTError(1, "Korisnik postoji u bazi"),
						HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<RESTError>(new RESTError(2, "Pogresan tip korisnika"), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getNastavnici() {
		return new ResponseEntity<Iterable>(nastavnikRepository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getNastavnikById(@PathVariable Integer id) {
		try {
			if (nastavnikRepository.exists(id)) {
				Nastavnik nastavnik = nastavnikRepository.findOne(id);
				return new ResponseEntity<Nastavnik>(nastavnik, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(3, "Korisnik ne postoji u bazi"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(4, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/jmbg")
	public ResponseEntity<?> getNastavnikByJmbg(@RequestParam String jmbg) {
		try {
			if (nastavnikRepository.findByJmbg(jmbg) != null) {
				Nastavnik nastavnik = nastavnikRepository.findByJmbg(jmbg);
				return new ResponseEntity<Nastavnik>(nastavnik, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(3, "Korisnik ne postoji u bazi"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(4, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteNastavnik(@PathVariable Integer id) {
		try {
			if (nastavnikRepository.exists(id)) {
				Nastavnik nastavnik = nastavnikRepository.findOne(id);
				nastavnikRepository.delete(nastavnik);
				return new ResponseEntity<Nastavnik>(nastavnik, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(3, "Korisnik ne postoji u bazi"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(4, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> modifyNastavnik(@PathVariable Integer id, @RequestBody OsobaDTO nastavnikDTO) {
		try {
			if (nastavnikRepository.exists(id)) {
				Nastavnik nastavnik = nastavnikRepository.findOne(id);
				if (!nastavnikDTO.getIme().isEmpty()) {
					nastavnik.setIme(nastavnikDTO.getIme());
				}
				if (!nastavnikDTO.getPrezime().isEmpty()) {
					nastavnik.setPrezime(nastavnikDTO.getPrezime());
				}
				if (!nastavnikDTO.getJmbg().isEmpty()) {
					nastavnik.setJmbg(nastavnikDTO.getJmbg());
				}
				if (!nastavnikDTO.getAdresa().isEmpty()) {
					nastavnik.setAdresa(nastavnikDTO.getAdresa());
				}
				if (nastavnikDTO.getDatumRodjenja() != null) {
					nastavnik.setDatumRodjenja(nastavnikDTO.getDatumRodjenja());
				}
				if (!nastavnikDTO.getEmail().isEmpty()) {
					nastavnik.setEmail(nastavnikDTO.getEmail());
				}
				nastavnikRepository.save(nastavnik);
				return new ResponseEntity<Nastavnik>(nastavnik, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(3, "Korisnik ne postoji u bazi"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(4, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*@RequestMapping(method = RequestMethod.PUT, value = "/{id}/predmet")
	public ResponseEntity<?> addPredmetToNastavnik(@PathVariable Integer id, @RequestParam Integer predmetId) {
		try {
			if (nastavnikRepository.exists(id)) {
				if (predmetRepository.exists(predmetId)) {
					Nastavnik nastavnik = nastavnikRepository.findOne(id);
					Predmet predmet = predmetRepository.findOne(predmetId);
					List<Predmet> predmeti = nastavnik.getPredmeti();
					predmeti.add(predmet);
					nastavnik.setPredmeti(predmeti);
					nastavnikRepository.save(nastavnik);
					return new ResponseEntity<Nastavnik>(nastavnik, HttpStatus.OK);
				}
				return new ResponseEntity<RESTError>(new RESTError(4, "Ne postoji predmet sa tom oznakom"),
						HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<RESTError>(new RESTError(2, "Ne postoji nastavnik sa tom oznakom"),
					HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(3, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}*/
}
