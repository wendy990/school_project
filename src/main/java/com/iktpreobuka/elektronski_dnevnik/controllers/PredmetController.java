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

import com.iktpreobuka.elektronski_dnevnik.entities.Predaje;
import com.iktpreobuka.elektronski_dnevnik.entities.Predmet;
import com.iktpreobuka.elektronski_dnevnik.entities.dto.PredmetDTO;
import com.iktpreobuka.elektronski_dnevnik.repositories.NastavnikRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.OdeljenjeRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.PredajeRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.PredmetRepository;
import com.iktpreobuka.elektronski_dnevnik.services.PredmetDao;

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
	public ResponseEntity<?> getPredmeti(Pageable pageable) {
		return new ResponseEntity<Page<Predmet>>(predmetRepository.findAll(pageable), HttpStatus.OK);
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
				if (!predmetDTO.getNaziv().isEmpty()) {
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

}
