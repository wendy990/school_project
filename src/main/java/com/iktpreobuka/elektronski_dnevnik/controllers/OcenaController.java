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
import com.iktpreobuka.elektronski_dnevnik.entities.Ocena;
import com.iktpreobuka.elektronski_dnevnik.entities.Predmet;
import com.iktpreobuka.elektronski_dnevnik.entities.Ucenik;
import com.iktpreobuka.elektronski_dnevnik.entities.dto.OcenaDTO;
import com.iktpreobuka.elektronski_dnevnik.repositories.NastavnikRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.OcenaRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.PredajeRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.PredmetRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.UcenikRepository;

@RestController
@RequestMapping(path = "/api/v1/ocena")
public class OcenaController {

	@Autowired
	private OcenaRepository ocenaRepository;

	@Autowired
	private PredmetRepository predmetRepository;

	@Autowired
	private UcenikRepository ucenikRepository;

	@Autowired
	private NastavnikRepository nastavnikRepository;
	
	@Autowired
	private PredajeRepository predajeRepository;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addOcenaToUcenik(@RequestParam Integer predmetId, @RequestParam Integer ucenikId,
			@RequestParam Integer nastavnikId, @RequestBody OcenaDTO ocenaDTO) {
		if (predmetRepository.exists(predmetId)) {
			if (ucenikRepository.exists(ucenikId)) {
				if (nastavnikRepository.exists(nastavnikId)) {
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
					ocena.setZakljucna(ocenaDTO.isZakljucna());
					ocenaRepository.save(ocena);
					return new ResponseEntity<Ocena>(ocena, HttpStatus.OK);
				}
				return new ResponseEntity<RESTError>(new RESTError(1, "Nastavnik ne postoji u bazi"),
						HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<RESTError>(new RESTError(2, "Ucenik ne postoji u bazi"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<RESTError>(new RESTError(3, "Predmet ne postoji u bazi"), HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Ocena> getOcene() {
		return ocenaRepository.findAll();
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
			return new ResponseEntity<RESTError>(new RESTError(4, "Ocena ne postoji u bazi"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(5, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
