package com.iktpreobuka.elektronski_dnevnik.controllers;

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
import com.iktpreobuka.elektronski_dnevnik.entities.Odeljenje;
import com.iktpreobuka.elektronski_dnevnik.entities.Predmet;
import com.iktpreobuka.elektronski_dnevnik.entities.Razred;
import com.iktpreobuka.elektronski_dnevnik.entities.dto.RazredDTO;
import com.iktpreobuka.elektronski_dnevnik.repositories.OdeljenjeRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.RazredRepository;

@RestController
@RequestMapping(path = "/api/v1/odeljenje")
public class OdeljenjeController {

	@Autowired
	private OdeljenjeRepository odeljenjeRepository;

	@Autowired
	private RazredRepository razredRepository;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createOdeljenje(@RequestBody RazredDTO odeljenjeDTO) {
		if (odeljenjeRepository.findByOznaka(odeljenjeDTO.getOznaka()) == null) {
			Odeljenje odeljenje = new Odeljenje();
			odeljenje.setOznaka(odeljenjeDTO.getOznaka());
			odeljenjeRepository.save(odeljenje);
			return new ResponseEntity<Odeljenje>(odeljenje, HttpStatus.OK);
		}
		return new ResponseEntity<RESTError>(new RESTError(1, "Postoji odeljenje sa tom oznakom"),
				HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		return new ResponseEntity<Iterable>(odeljenjeRepository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getOdeljenjeById(@PathVariable Integer id) {
		try {
			if (odeljenjeRepository.exists(id)) {
				Odeljenje odeljenje = odeljenjeRepository.findOne(id);
				return new ResponseEntity<Odeljenje>(odeljenje, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(3, "Odeljenje ne postoji u bazi"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(4, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public ResponseEntity<?> modifyOdeljenjeById(@PathVariable Integer id, @RequestBody RazredDTO odeljenjeDTO) {
		try {
			if (odeljenjeRepository.exists(id)) {
				Odeljenje odeljenje = odeljenjeRepository.findOne(id);
				odeljenje.setOznaka(odeljenjeDTO.getOznaka());
				odeljenjeRepository.save(odeljenje);
				return new ResponseEntity<Odeljenje>(odeljenje, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(2, "Ne postoji odeljenje sa tom oznakom"),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(4, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteOdeljenjeById(@PathVariable Integer id) {
		try {
			if (odeljenjeRepository.exists(id)) {
				Odeljenje odeljenje = odeljenjeRepository.findOne(id);
				odeljenjeRepository.delete(odeljenje);
				return new ResponseEntity<Odeljenje>(odeljenje, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(2, "Ne postoji odeljenje sa tom oznakom"),
					HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(4, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/razred")
	public ResponseEntity<?> addOdeljenjeToRazred(@PathVariable Integer id, @RequestParam Integer razredId) {
		try {
			if (odeljenjeRepository.exists(id)) {
				if (razredRepository.exists(razredId)) {
					Odeljenje odeljenje = odeljenjeRepository.findOne(id);
					Razred razred = razredRepository.findOne(razredId);
					odeljenje.setRazred(razred);
					odeljenjeRepository.save(odeljenje);
					return new ResponseEntity<Odeljenje>(odeljenje, HttpStatus.OK);
				}
				return new ResponseEntity<RESTError>(new RESTError(2, "Ne postoji razred"), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<RESTError>(new RESTError(3, "Ne postoji odeljenje"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(4, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
