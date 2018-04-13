package com.iktpreobuka.elektronski_dnevnik.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.elektronski_dnevnik.entities.Odeljenje;
import com.iktpreobuka.elektronski_dnevnik.entities.Predaje;
import com.iktpreobuka.elektronski_dnevnik.entities.Predmet;
import com.iktpreobuka.elektronski_dnevnik.entities.Roditelj;
import com.iktpreobuka.elektronski_dnevnik.entities.Ucenik;
import com.iktpreobuka.elektronski_dnevnik.entities.dto.PredmetDTO;
import com.iktpreobuka.elektronski_dnevnik.repositories.NastavnikRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.OdeljenjeRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.PredajeRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.PredmetRepository;
import com.iktpreobuka.elektronski_dnevnik.services.PredmetDao;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/api/v1/predmet")
public class PredmetController {

	@Autowired
	private PredmetRepository predmetRepository;

	@Autowired
	private NastavnikRepository nastavnikRepository;

	@Autowired
	private OdeljenjeRepository odeljenjeRepository;

	@Autowired
	private PredajeRepository predajeRepository;

	@Autowired
	private PredmetDao predmetDao;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createPredmet(@RequestBody PredmetDTO predmetDTO) {
		if (predmetRepository.findPredmetByNaziv(predmetDTO.getNaziv()) == null) {
			Predmet predmet = new Predmet();
			predmet.setNaziv(predmetDTO.getNaziv());
			predmet.setFond(predmetDTO.getFond());
			predmetRepository.save(predmet);
			return new ResponseEntity<Predmet>(predmet, HttpStatus.OK);
		}
		return new ResponseEntity<RESTError>(new RESTError(1, "Predmet postoji u bazi"), HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Iterable<Predmet>> getPredmeti() {
		return new ResponseEntity<Iterable<Predmet>>(predmetRepository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getPredmetById(@PathVariable Integer id) {
		try {
			if (predmetRepository.exists(id)) {
				Predmet predmet = predmetRepository.findOne(id);
				return new ResponseEntity<Predmet>(predmet, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(2, "Predmet ne postoji u bazi"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(5, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/naziv")
	public ResponseEntity<?> getPredmetByNaziv(@RequestParam String naziv) {
		try {
				List<Predmet> predmeti = predmetRepository.findPredmetiByNazivStartingWith(naziv);
				return new ResponseEntity<List<Predmet>>(predmeti, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(4, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deletePredmet(@PathVariable Integer id) {
		try {
			if (predmetRepository.exists(id)) {
				Predmet predmet = predmetRepository.findOne(id);
				predmetRepository.delete(predmet);
				return new ResponseEntity<Predmet>(predmet, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(2, "Predmet ne postoji u bazi"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(5, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> modifyPredmetById(@PathVariable Integer id, @RequestBody PredmetDTO predmetDTO) {
		try {
			if (predmetRepository.exists(id)) {
				Predmet predmet = predmetRepository.findOne(id);
				if (predmetDTO.getNaziv() != null) {
					predmet.setNaziv(predmetDTO.getNaziv());
				}
				if (predmetDTO.getFond() != null) {
					predmet.setFond(predmetDTO.getFond());
				}
				predmetRepository.save(predmet);
				return new ResponseEntity<Predmet>(predmet, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(2, "Predmet ne postoji u bazi"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(5, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.PUT, value = "/{predmetId}/predaje")
	public ResponseEntity<?> addPredmetToNastavnikAndOdeljenje(@PathVariable Integer predmetId,
			@RequestParam Integer nastavnikId, @RequestParam Integer odeljenjeId) {
		try {
			if (predmetRepository.exists(predmetId)) {
				if (nastavnikRepository.exists(nastavnikId)) {
					if (odeljenjeRepository.exists(odeljenjeId)) {
						Predaje predaje = new Predaje(nastavnikId, predmetId, odeljenjeId);
						predajeRepository.save(predaje);
						Predmet predmet = predmetRepository.findOne(predmetId);
						return new ResponseEntity<Predmet>(predmet, HttpStatus.OK);
					}
					return new ResponseEntity<RESTError>(new RESTError(3, "Odeljenje ne postoji u bazi"),
							HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<RESTError>(new RESTError(4, "Nastavnik ne postoji u bazi"),
						HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<RESTError>(new RESTError(2, "Predmet ne postoji u bazi"), HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(5, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/

	@RequestMapping(method = RequestMethod.PUT, value = "/{predmetId}/predaje")
	public ResponseEntity<?> addPredmetToNastavnikAndOdeljenje(@PathVariable Integer predmetId,
			@RequestParam Integer nastavnikId, @RequestParam Integer odeljenjeId) {
		try {
			if (predmetRepository.exists(predmetId)) {
				if (nastavnikRepository.exists(nastavnikId)) {
					if (odeljenjeRepository.exists(odeljenjeId)) {
						Predmet predmet = predmetRepository.findOne(predmetId);
						Odeljenje odeljenje = odeljenjeRepository.findOne(odeljenjeId);
						if(predmet.getNaziv().endsWith(odeljenje.getRazred().getRazred().toString())) {
						Predaje predaje = new Predaje(nastavnikId, predmetId, odeljenjeId);
						predajeRepository.save(predaje);				
						return new ResponseEntity<Predmet>(predmet, HttpStatus.OK);
					}
						return new ResponseEntity<RESTError>(new RESTError(3, "Predmet ne mozete dodeliti odabranom odeljenju"),
							HttpStatus.NOT_FOUND);
					}
					return new ResponseEntity<RESTError>(new RESTError(3, "Odeljenje ne postoji u bazi"),
							HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<RESTError>(new RESTError(4, "Nastavnik ne postoji u bazi"),
						HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<RESTError>(new RESTError(2, "Predmet ne postoji u bazi"), HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(5, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*@RequestMapping(method = RequestMethod.PUT, value = "/{predmetId}/razred")
	public ResponseEntity<?> addPredmetToRazred(@PathVariable Integer predmetId, @RequestParam Integer razredId) {
		try {
			if (predmetRepository.exists(predmetId)) {
				if (razredRepository.exists(razredId)) {
					Predmet predmet = predmetRepository.findOne(predmetId);
					Razred razred = razredRepository.findOne(razredId);
					predmet.setRazred(razred);
					predmetRepository.save(predmet);
					return new ResponseEntity<Predmet>(predmet, HttpStatus.OK);
				}
				return new ResponseEntity<RESTError>(new RESTError(5, "Razred ne postoji u bazi"),
						HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<RESTError>(new RESTError(2, "Predmet ne postoji u bazi"), HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(3, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}*/

	@RequestMapping(method = RequestMethod.GET, value = "/ucenik/{ucenikId}")
	public ResponseEntity<?> getPredmetiByUcenikId(@PathVariable Integer ucenikId) {
		return new ResponseEntity<List<Predmet>>(predmetDao.findPredmetByUcenikId(ucenikId), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/nastavnik/{nastavnikId}")
	public ResponseEntity<?> getPredmetiByNastavnikId(@PathVariable Integer nastavnikId) {
		return new ResponseEntity<List<Predmet>>(predmetDao.findPredmetByNastavnikId(nastavnikId), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/odeljenje/{odeljenjeId}")
	public ResponseEntity<?> getPredmetiByOdeljenjeId(@PathVariable Integer odeljenjeId) {
		return new ResponseEntity<List<Predmet>>(predmetDao.findPredmetByOdeljenjeId(odeljenjeId), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/no/{nastavnikId}/{odeljenjeId}")
	public ResponseEntity<?> getPredmetiByNastavnikAndOdeljenje(@PathVariable Integer nastavnikId, @PathVariable Integer odeljenjeId) {
		return new ResponseEntity<List<Predmet>>(predmetDao.findPredmetByNastavnikIdAndOdeljenjeId(nastavnikId, odeljenjeId), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{nastavnikId}/{ucenikId}")
	public ResponseEntity<?> getPredmetiByNastavnikAndUcenik(@PathVariable Integer nastavnikId, @PathVariable Integer ucenikId) {
		return new ResponseEntity<List<Predmet>>(predmetDao.findPredmetByNastavnikAndUcenik(nastavnikId, ucenikId), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/slusa/{predmetId}")
	public ResponseEntity<?> getUceniciByPredmetId(@PathVariable Integer predmetId) {
		return new ResponseEntity<List<Ucenik>>(predmetDao.findUceniciByPredmetId(predmetId), HttpStatus.OK);
	}

}
