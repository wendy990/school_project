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

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.elektronski_dnevnik.config.Views;
import com.iktpreobuka.elektronski_dnevnik.entities.Roditelj;
import com.iktpreobuka.elektronski_dnevnik.entities.Ucenik;
import com.iktpreobuka.elektronski_dnevnik.entities.dto.OsobaDTO;
import com.iktpreobuka.elektronski_dnevnik.enums.TipKorisnika;
import com.iktpreobuka.elektronski_dnevnik.repositories.RoditeljRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.UcenikRepository;
import com.iktpreobuka.elektronski_dnevnik.services.RoditeljDao;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/api/v1/roditelj")
public class RoditeljController {

	@Autowired
	private RoditeljRepository roditeljRepository;

	@Autowired
	private UcenikRepository ucenikRepository;
	
	@Autowired
	private RoditeljDao roditeljDao;

	//@CrossOrigin(origins = "http://localhost:4200", exposedHeaders = "*")
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createRoditelj(@RequestBody OsobaDTO roditeljDTO) {
		if (roditeljDTO.getTipKorisnika().equals(TipKorisnika.RODITELJ)) {
			if (roditeljRepository.findRoditeljByJmbg(roditeljDTO.getJmbg()) == null) {
				Roditelj roditelj = new Roditelj();
				roditelj.setTipKorisnika(roditeljDTO.getTipKorisnika());
				roditelj.setIme(roditeljDTO.getIme());
				roditelj.setPrezime(roditeljDTO.getPrezime());
				roditelj.setEmail(roditeljDTO.getEmail());
				roditelj.setJmbg(roditeljDTO.getJmbg());
				roditelj.setDatumRodjenja(roditeljDTO.getDatumRodjenja());
				roditelj.setAdresa(roditeljDTO.getAdresa());
				roditeljRepository.save(roditelj);
				return new ResponseEntity<Roditelj>(roditelj, HttpStatus.OK);
			} else {
				return new ResponseEntity<RESTError>(new RESTError(1, "Korisnik postoji u bazi"),
						HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<RESTError>(new RESTError(2, "Pogresan tip korisnika"), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Iterable<Roditelj>> getRoditelji() {
		return new ResponseEntity<Iterable<Roditelj>>(roditeljRepository.findAll(), HttpStatus.OK);
	}
	
	/*@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getRoditelji(Pageable pageable) {
		return new ResponseEntity<Page<Roditelj>>(roditeljRepository.findAll(pageable), HttpStatus.OK);
	}*/


	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getRoditeljById(@PathVariable Integer id) {
		try {
			if (roditeljRepository.exists(id)) {
				Roditelj roditelj = roditeljRepository.findOne(id);
				return new ResponseEntity<Roditelj>(roditelj, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(3, "Korisnik ne postoji u bazi"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(4, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*@RequestMapping(method = RequestMethod.GET, value = "/jmbg")
	public ResponseEntity<?> getRoditeljByJmbg(@RequestParam String jmbg) {
		try {
			if (roditeljRepository.findRoditeljByJmbg(jmbg) != null) {
				Roditelj roditelj = roditeljRepository.findRoditeljByJmbgStartingWith(jmbg);
				return new ResponseEntity<Roditelj>(roditelj, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(3, "Korisnik ne postoji u bazi"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(4, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/
	
	@RequestMapping(method = RequestMethod.GET, value = "/jmbg")
	public ResponseEntity<?> getRoditeljByJmbg(@RequestParam String jmbg) {
		try {
				List<Roditelj> roditelji = roditeljRepository.findRoditeljiByJmbgStartingWith(jmbg);
				return new ResponseEntity<List<Roditelj>>(roditelji, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(4, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteRoditeljById(@PathVariable Integer id) {
		try {
			if (roditeljRepository.exists(id)) {
				Roditelj roditelj = roditeljRepository.findOne(id);
				roditeljRepository.delete(roditelj);
				return new ResponseEntity<Roditelj>(roditelj, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(3, "Korisnik ne postoji u bazi"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(4, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> modifyRoditeljById(@PathVariable Integer id, @RequestBody OsobaDTO roditeljDTO) {
		try {
			if (roditeljRepository.exists(id)) {
				Roditelj roditelj = roditeljRepository.findOne(id);
				if(roditeljDTO.getTipKorisnika() != null) {
					if(roditeljDTO.getTipKorisnika().equals(TipKorisnika.RODITELJ)) {
						roditelj.setTipKorisnika(roditeljDTO.getTipKorisnika());
					}else{
						return new ResponseEntity<RESTError>(new RESTError(2, "Pogresan tip korisnika"), HttpStatus.BAD_REQUEST);
				}
				}
				if (roditeljDTO.getIme() != null) {
					roditelj.setIme(roditeljDTO.getIme());
				}
				if (roditeljDTO.getPrezime() != null) {
					roditelj.setPrezime(roditeljDTO.getPrezime());
				}
				if (roditeljDTO.getEmail() != null) {
					roditelj.setEmail(roditeljDTO.getEmail());
				}
				if (roditeljDTO.getJmbg() != null) {
					roditelj.setJmbg(roditeljDTO.getJmbg());
				}
				if (roditeljDTO.getDatumRodjenja() != null) {
					roditelj.setDatumRodjenja(roditeljDTO.getDatumRodjenja());
				}
				if (roditeljDTO.getAdresa() != null) {
					roditelj.setAdresa(roditeljDTO.getAdresa());
				}
				roditeljRepository.save(roditelj);
				return new ResponseEntity<Roditelj>(roditelj, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(3, "Korisnik ne postoji u bazi"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(4, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/ucenik")
	public ResponseEntity<?> addUcenikToRoditelj(@PathVariable Integer id, @RequestParam Integer ucenikId) {
		try {
			if (roditeljRepository.exists(id)) {
				if (ucenikRepository.exists(ucenikId)) {
					Roditelj roditelj = roditeljRepository.findOne(id);
					Ucenik ucenik = ucenikRepository.findOne(ucenikId);
					if (roditelj.getDeca().contains(ucenik)) {
						return new ResponseEntity<RESTError>(new RESTError(6, "Ucenik je vec dodeljen roditelju"),
								HttpStatus.BAD_REQUEST);
					} else {
						List<Ucenik> deca = roditelj.getDeca();
						deca.add(ucenik);
						roditelj.setDeca(deca);
						roditeljRepository.save(roditelj);
						return new ResponseEntity<Roditelj>(roditelj, HttpStatus.OK);
					}
				}
				return new ResponseEntity<RESTError>(new RESTError(3, "Korisnik ne postoji u bazi"),
						HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<RESTError>(new RESTError(3, "Korisnik ne postoji u bazi"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(4, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/admin/odeljenje/{odeljenjeId}")
	@JsonView(Views.Admin.class)
	public ResponseEntity<?> getRoditeljiByOdeljenjeIdForAdmin(@PathVariable Integer odeljenjeId) {
		return new ResponseEntity<List<Roditelj>>(roditeljDao.findRoditeljiByOdeljenjeId(odeljenjeId), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/private/odeljenje/{odeljenjeId}")
	@JsonView(Views.Private.class)
	public ResponseEntity<?> getRoditeljiByOdeljenjeIdForPrivate(@PathVariable Integer odeljenjeId) {
		return new ResponseEntity<List<Roditelj>>(roditeljDao.findRoditeljiByOdeljenjeId(odeljenjeId), HttpStatus.OK);
	}

}
