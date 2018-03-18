package com.iktpreobuka.elektronski_dnevnik.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.elektronski_dnevnik.entities.Razred;
import com.iktpreobuka.elektronski_dnevnik.entities.dto.RazredDTO;
import com.iktpreobuka.elektronski_dnevnik.repositories.RazredRepository;

@RestController
@RequestMapping(path = "/api/v1/razred")
public class RazredController {

	@Autowired
	private RazredRepository razredRepository;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createRazred(@RequestBody RazredDTO razredDTO) {
		if (razredRepository.findByRazred(razredDTO.getRazred()) == null) {
			Razred razred = new Razred();
			razred.setRazred(razredDTO.getRazred());
			razredRepository.save(razred);
			return new ResponseEntity<Razred>(razred, HttpStatus.OK);
		}
		return new ResponseEntity<RESTError>(new RESTError(1, "Postoji odeljenje sa tom oznakom"),
				HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll(Pageable pageable) {
		return new ResponseEntity<Page<Razred>>(razredRepository.findAll(pageable), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getRazredById(@PathVariable Integer id) {
		try {
			if (razredRepository.exists(id)) {
				Razred razred = razredRepository.findOne(id);
				return new ResponseEntity<Razred>(razred, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(3, "Razred ne postoji u bazi"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(4, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public ResponseEntity<?> modifyRazredById(@PathVariable Integer id, @RequestBody RazredDTO razredDTO) {
		try {
			if (razredRepository.exists(id)) {
				Razred razred = razredRepository.findOne(id);
				razred.setRazred(razredDTO.getRazred());
				razredRepository.save(razred);
				return new ResponseEntity<Razred>(razred, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(2, "Ne postoji razred sa tom oznakom"),
					HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(3, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteRazredById(@PathVariable Integer id) {
		try {
			if (razredRepository.exists(id)) {
				Razred razred = razredRepository.findOne(id);
				razredRepository.delete(razred);
				return new ResponseEntity<Razred>(razred, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(2, "Ne postoji razred sa tom oznakom"),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(4, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
