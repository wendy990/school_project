package com.iktpreobuka.elektronski_dnevnik.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.elektronski_dnevnik.entities.Odeljenje;
import com.iktpreobuka.elektronski_dnevnik.entities.Ucenik;
import com.iktpreobuka.elektronski_dnevnik.entities.dto.OsobaDTO;
import com.iktpreobuka.elektronski_dnevnik.enums.TipKorisnika;
import com.iktpreobuka.elektronski_dnevnik.repositories.OdeljenjeRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.UcenikRepository;
import com.iktpreobuka.elektronski_dnevnik.services.UcenikDao;

@RestController
@RequestMapping(path = "/api/v1/ucenik")
public class UcenikController {

	@Autowired
	private UcenikRepository ucenikRepository;

	@Autowired
	private OdeljenjeRepository odeljenjeRepository;
	
	@Autowired
	private UcenikDao ucenikDao;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createUcenik(@RequestBody OsobaDTO ucenikDTO) {
		if (ucenikDTO.getTipKorisnika().equals(TipKorisnika.UCENIK)) {
			if (ucenikRepository.findByJmbg(ucenikDTO.getJmbg()) == null) {
				Ucenik ucenik = new Ucenik();
				ucenik.setTipKorisnika(ucenikDTO.getTipKorisnika());
				ucenik.setIme(ucenikDTO.getIme());
				ucenik.setPrezime(ucenikDTO.getPrezime());
				ucenik.setEmail(ucenikDTO.getEmail());
				ucenik.setJmbg(ucenikDTO.getJmbg());
				ucenik.setDatumRodjenja(ucenikDTO.getDatumRodjenja());
				ucenik.setAdresa(ucenikDTO.getAdresa());
				ucenikRepository.save(ucenik);
				return new ResponseEntity<Ucenik>(ucenik, HttpStatus.OK);
			} else {
				return new ResponseEntity<RESTError>(new RESTError(1, "Korisnik postoji u bazi"),
						HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<RESTError>(new RESTError(2, "Pogresan tip korisnika"), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getUcenici(Pageable pageable) {
		return new ResponseEntity<Page<Ucenik>>(ucenikRepository.findAll(pageable), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getUcenikById(@PathVariable Integer id) {
		try {
			if (ucenikRepository.exists(id)) {
				Ucenik ucenik = ucenikRepository.findOne(id);
				return new ResponseEntity<Ucenik>(ucenik, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(3, "Korisnik ne postoji u bazi"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(4, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/jmbg")
	public ResponseEntity<?> getUcenikByJmbg(@RequestParam String jmbg) {
		try {
			if (ucenikRepository.findByJmbg(jmbg) != null) {
				Ucenik ucenik = ucenikRepository.findByJmbg(jmbg);
				return new ResponseEntity<Ucenik>(ucenik, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(3, "Korisnik ne postoji u bazi"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(4, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteUcenikById(@PathVariable Integer id) {
		try {
			if (ucenikRepository.exists(id)) {
				Ucenik ucenik = ucenikRepository.findOne(id);
				ucenikRepository.delete(ucenik);
				return new ResponseEntity<Ucenik>(ucenik, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(3, "Korisnik ne postoji u bazi"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(4, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> modifyUcenikById(@PathVariable Integer id, @RequestBody OsobaDTO ucenikDTO) {
		try {
			if (ucenikRepository.exists(id)) {
				Ucenik ucenik = ucenikRepository.findOne(id);
				if (!ucenikDTO.getIme().isEmpty()) {
					ucenik.setIme(ucenikDTO.getIme());
				}
				if (!ucenikDTO.getPrezime().isEmpty()) {
					ucenik.setPrezime(ucenikDTO.getPrezime());
				}
				if (!ucenikDTO.getEmail().isEmpty()) {
					ucenik.setEmail(ucenikDTO.getEmail());
				}
				if (!ucenikDTO.getJmbg().isEmpty()) {
					ucenik.setJmbg(ucenikDTO.getJmbg());
				}
				if (ucenikDTO.getDatumRodjenja() != null) {
					ucenik.setDatumRodjenja(ucenikDTO.getDatumRodjenja());
				}
				if (!ucenikDTO.getAdresa().isEmpty()) {
					ucenik.setAdresa(ucenikDTO.getAdresa());
				}
				ucenikRepository.save(ucenik);
				return new ResponseEntity<Ucenik>(ucenik, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(3, "Korisnik ne postoji u bazi"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(4, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/odeljenje")
	public ResponseEntity<?> addUcenikToOdeljenje(@PathVariable Integer id, @RequestParam Integer odeljenjeId) {
		try {
			if (ucenikRepository.exists(id)) {
				if (odeljenjeRepository.exists(odeljenjeId)) {
					Ucenik ucenik = ucenikRepository.findOne(id);
					Odeljenje odeljenje = odeljenjeRepository.findOne(odeljenjeId);
					if(odeljenje.getUcenici().contains(ucenik)) {
						return new ResponseEntity<RESTError>(new RESTError(6, "Ucenik vec pripada odeljenju"), HttpStatus.BAD_REQUEST);
					}
					else {
					ucenik.setOdeljenje(odeljenje);
					ucenikRepository.save(ucenik);
					return new ResponseEntity<Ucenik>(ucenik, HttpStatus.OK);
				}
				}
				return new ResponseEntity<RESTError>(new RESTError(5, "Odeljenje ne postoji"), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<RESTError>(new RESTError(3, "Korisnik ne postoji u bazi"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(4, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/roditelj/{roditeljId}")
	public ResponseEntity<?> getUceniciByRoditeljId(@PathVariable Integer roditeljId) {
		return new ResponseEntity<List<Ucenik>>(ucenikDao.findUceniciByRoditeljId(roditeljId), HttpStatus.OK);
	}
	
	/*@RequestMapping(method = RequestMethod.GET, value = "/predmet/{predmetId}")
	public ResponseEntity<?> getUceniciByPredmetId(@PathVariable Integer predmetId) {
		return new ResponseEntity<List<Ucenik>>(ucenikDao.findUceniciByPredmetId(predmetId), HttpStatus.OK);
	}*/
	
	@RequestMapping(method = RequestMethod.GET, value = "/odeljenje/{odeljenjeId}")
	public ResponseEntity<?> getUceniciByOdeljenjeId(@PathVariable Integer odeljenjeId) {
		return new ResponseEntity<List<Ucenik>>(ucenikDao.findUceniciByOdeljenjeId(odeljenjeId), HttpStatus.OK);
	}
}
