package com.iktpreobuka.elektronski_dnevnik.controllers;

import java.util.ArrayList;
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

import com.iktpreobuka.elektronski_dnevnik.entities.Nastavnik;
import com.iktpreobuka.elektronski_dnevnik.entities.Ocena;
import com.iktpreobuka.elektronski_dnevnik.entities.Predmet;
import com.iktpreobuka.elektronski_dnevnik.entities.Ucenik;
import com.iktpreobuka.elektronski_dnevnik.entities.dto.OcenaDTO;
import com.iktpreobuka.elektronski_dnevnik.repositories.NastavnikRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.OcenaRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.PredmetRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.UcenikRepository;
import com.iktpreobuka.elektronski_dnevnik.services.OcenaDao;
import com.iktpreobuka.elektronski_dnevnik.services.OdeljenjeDao;
import com.iktpreobuka.elektronski_dnevnik.services.PredmetDao;

@RestController
@RequestMapping(path = "/api/v1/ocena")
public class OcenaController {

	@Autowired
	private OcenaRepository ocenaRepository;

	@Autowired
	private OcenaDao ocenaDao;

	@Autowired
	private PredmetRepository predmetRepository;

	@Autowired
	private UcenikRepository ucenikRepository;

	@Autowired
	private NastavnikRepository nastavnikRepository;

	@Autowired
	private PredmetDao predmetDao;
	
	@Autowired
	private OdeljenjeDao odeljenjeDao;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addOcenaToUcenik(@RequestParam Integer predmetId, @RequestParam Integer ucenikId,
			@RequestParam Integer nastavnikId, @RequestBody OcenaDTO ocenaDTO) {
		if (predmetRepository.exists(predmetId)) {
			if (ucenikRepository.exists(ucenikId)) {
				if (nastavnikRepository.exists(nastavnikId)) {
					if ((ocenaDTO.getVrednost() >= 1) && (ocenaDTO.getVrednost() <= 5)) {
						if ((ocenaDTO.getPolugodiste() == 1) || (ocenaDTO.getPolugodiste() == 2)) {
							Predmet predmet = predmetRepository.findOne(predmetId);
							Ucenik ucenik = ucenikRepository.findOne(ucenikId);
							Nastavnik nastavnik = nastavnikRepository.findOne(nastavnikId);
							if (predmetDao.findPredmetByNastavnikId(nastavnikId).contains(predmet)) {
								if (odeljenjeDao.findOdeljenjeByNastavnikId(nastavnikId).contains(ucenik.getOdeljenje())) {
									Ocena ocena = new Ocena();
									ocena.setPredmet(predmet);
									ocena.setUcenik(ucenik);
									ocena.setNastavnik(nastavnik);
									ocena.setTipOcene(ocenaDTO.getTipOcene());
									ocena.setVrednost(ocenaDTO.getVrednost());
									ocena.setDatumUnosa(ocenaDTO.getDatumUnosa());
									ocena.setPolugodiste(ocenaDTO.getPolugodiste());
									ocena.setDeleted(false);
									ocenaRepository.save(ocena);
									return new ResponseEntity<Ocena>(ocena, HttpStatus.OK);
								}
								return new ResponseEntity<RESTError>(new RESTError(9, "Predmet i nastavnik ne pripadaju odeljenju ucenika"),HttpStatus.BAD_REQUEST);
							}
							return new ResponseEntity<RESTError>(new RESTError(10, "Nastavnik ne predaje odabrani predmet"), HttpStatus.BAD_REQUEST);
						}
						return new ResponseEntity<RESTError>(new RESTError(1, "Oznake polugodista su 1 ili 2"), HttpStatus.BAD_REQUEST);
					}
					return new ResponseEntity<RESTError>(new RESTError(2, "Ocena mora biti izmedju 1 i 5"), HttpStatus.BAD_REQUEST);
				}
				return new ResponseEntity<RESTError>(new RESTError(3, "Nastavnik ne postoji u bazi"), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<RESTError>(new RESTError(4, "Ucenik ne postoji u bazi"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<RESTError>(new RESTError(5, "Predmet ne postoji u bazi"), HttpStatus.NOT_FOUND);
	}
	
	/*	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addOcenaToUcenik(@RequestParam Integer predmetId, @RequestParam Integer ucenikId,
			@RequestParam Integer nastavnikId, @RequestBody OcenaDTO ocenaDTO) {
		if (predmetRepository.exists(predmetId)) {
			if (ucenikRepository.exists(ucenikId)) {
				if (nastavnikRepository.exists(nastavnikId)) {
					if ((ocenaDTO.getVrednost() >= 1) && (ocenaDTO.getVrednost() <= 5)) {
						if ((ocenaDTO.getPolugodiste() == 1) || (ocenaDTO.getPolugodiste() == 2)) {
							Predmet predmet = predmetRepository.findOne(predmetId);
							Ucenik ucenik = ucenikRepository.findOne(ucenikId);
							Nastavnik nastavnik = nastavnikRepository.findOne(nastavnikId);
							Ocena ocena = new Ocena();
							ocena.setPredmet(predmet);
							ocena.setUcenik(ucenik);
							ocena.setNastavnik(nastavnik);
							ocena.setTipOcene(ocenaDTO.getTipOcene());
							ocena.setVrednost(ocenaDTO.getVrednost());
							ocena.setDatumUnosa(ocenaDTO.getDatumUnosa());
							ocena.setPolugodiste(ocenaDTO.getPolugodiste());
							ocena.setDeleted(false);
							ocenaRepository.save(ocena);
							return new ResponseEntity<Ocena>(ocena, HttpStatus.OK);
						}
						return new ResponseEntity<RESTError>(new RESTError(1, "Oznake polugodista su 1 ili 2"),
								HttpStatus.BAD_REQUEST);
					}
					return new ResponseEntity<RESTError>(new RESTError(2, "Ocena mora biti izmedju 1 i 5"),
							HttpStatus.BAD_REQUEST);
				}
				return new ResponseEntity<RESTError>(new RESTError(3, "Nastavnik ne postoji u bazi"),
						HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<RESTError>(new RESTError(4, "Ucenik ne postoji u bazi"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<RESTError>(new RESTError(5, "Predmet ne postoji u bazi"), HttpStatus.NOT_FOUND);
	}
*/


	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getOcene(Pageable pageable) {
		return new ResponseEntity<Page<Ocena>>(ocenaRepository.findAll(pageable), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getOcenaById(@PathVariable Integer id) {
		try {
			if (ocenaRepository.exists(id)) {
				Ocena ocena = ocenaRepository.findOne(id);
				return new ResponseEntity<Ocena>(ocena, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(6, "Ocena ne postoji u bazi"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(7, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteOcena(@PathVariable Integer id) {
		try {
			if (ocenaRepository.exists(id)) {
				Ocena ocena = ocenaRepository.findOne(id);
				ocena.setDeleted(true);
				ocenaRepository.save(ocena);
				return new ResponseEntity<Ocena>(ocena, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(6, "Ocena ne postoji u bazi"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(7, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/ucenik/{ucenikId}/{predmetId}")
	public ResponseEntity<?> getOceneByUcenikId(@PathVariable Integer ucenikId, @PathVariable Integer predmetId) {
		return new ResponseEntity<List<Ocena>>(ocenaDao.findOceneByUcenikId(predmetId, ucenikId), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/polugodiste1/{ucenikId}/{predmetId}")
	public ResponseEntity<?> izracunajZakljucnaOcenaPolugodiste1(@PathVariable Integer ucenikId,
			@PathVariable Integer predmetId) {
		List<Ocena> ocene = new ArrayList<>();
		ocene = ocenaDao.findOceneByPolugodiste1(predmetId, ucenikId);
		Double sum = 0.0;
		for (Ocena o : ocene) {
			sum += o.getVrednost();
		}
		return new ResponseEntity<Double>(sum / ocene.size(), HttpStatus.OK);
	}

	/*
	 @RequestMapping(method = RequestMethod.GET, value ="/{polugodiste}/polugodiste") 
	 public ResponseEntity<?>getZakljucnaOcenaPolugodiste1(@PathVariable Integer polugodiste,
	 @RequestParam TipOcene tipOcene) { 
	 // Ocena ocena = ocenaRepository.findOcenaByTipOceneIPolugodiste(tipOcene,polugodiste);
	   if (ocenaRepository.findOcenaByTipOceneAndPolugodiste(tipOcene, polugodiste) != null){ 
	   Ocena ocena =  ocenaRepository.findOcenaByTipOceneAndPolugodiste(tipOcene, polugodiste);
	   return new ResponseEntity<Ocena>(ocena, HttpStatus.OK); 
	   } 
	   return new ResponseEntity<RESTError>(new RESTError(8, "Ocena iz prvog polugodista nije zakljucena"), HttpStatus.NOT_FOUND); }
	 */

	@RequestMapping(method = RequestMethod.GET, value = "/zakljucna/{ucenikId}/{predmetId}")
	public ResponseEntity<?> izracunajZakljucnaOcena(@PathVariable Integer ucenikId, @PathVariable Integer predmetId) {
		if (ocenaDao.findZakljucnaOcenaPolugodiste1(predmetId, ucenikId).isEmpty()) {
			return new ResponseEntity<RESTError>(new RESTError(8, "Ocena iz prvog polugodista nije zakljucena"),
					HttpStatus.NOT_FOUND);
		} else {
			List<Ocena> ocene = new ArrayList<>();
			ocene = ocenaDao.findOceneByPolugodiste2(predmetId, ucenikId);
			Double sum = 0.0;
			for (Ocena o : ocene) {
				sum += o.getVrednost();
			}
			return new ResponseEntity<Double>(sum / ocene.size(), HttpStatus.OK);
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/pol/{ucenikId}/{predmetId}")
	public ResponseEntity<?> getZakljucnaPolugodiste1(@PathVariable Integer ucenikId, @PathVariable Integer predmetId) {
		if (ocenaDao.findZakljucnaOcenaPolugodiste1(predmetId, ucenikId).isEmpty()) {
			return new ResponseEntity<RESTError>(new RESTError(8, "Ocena iz prvog polugodista nije zakljucena"),
					HttpStatus.NOT_FOUND);
		}else {
		return new ResponseEntity<List<Ocena>>(ocenaDao.findZakljucnaOcenaPolugodiste1(predmetId, ucenikId),
				HttpStatus.OK);
		}
	}
}
