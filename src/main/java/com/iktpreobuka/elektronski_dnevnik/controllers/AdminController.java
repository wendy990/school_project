package com.iktpreobuka.elektronski_dnevnik.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.elektronski_dnevnik.entities.Administrator;
import com.iktpreobuka.elektronski_dnevnik.entities.Nastavnik;
import com.iktpreobuka.elektronski_dnevnik.entities.dto.OsobaDTO;
import com.iktpreobuka.elektronski_dnevnik.enums.TipKorisnika;
import com.iktpreobuka.elektronski_dnevnik.repositories.AdminRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

	@Autowired
	private AdminRepository adminRepository;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createAdministrator(@RequestBody OsobaDTO adminDTO) {
		if (adminDTO.getTipKorisnika().equals(TipKorisnika.ADMINISTRATOR)) {
			if (adminRepository.findByJmbg(adminDTO.getJmbg()) == null) {
				Administrator admin = new Administrator();
				admin.setTipKorisnika(adminDTO.getTipKorisnika());
				admin.setIme(adminDTO.getIme());
				admin.setPrezime(adminDTO.getPrezime());
				admin.setDatumRodjenja(adminDTO.getDatumRodjenja());
				admin.setJmbg(adminDTO.getJmbg());
				admin.setAdresa(adminDTO.getAdresa());
				admin.setEmail(adminDTO.getEmail());
				adminRepository.save(admin);
				return new ResponseEntity<Administrator>(admin, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(1, "Korisnik postoji u bazi"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<RESTError>(new RESTError(2, "Pogresan tip korisnika"), HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAdministratori(Pageable pageable) {
		return new ResponseEntity<Page<Administrator>>(adminRepository.findAll(pageable), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getAdministratorById(@PathVariable Integer id) {
		try {
			if (adminRepository.exists(id)) {
				Administrator administrator = adminRepository.findOne(id);
				return new ResponseEntity<Administrator>(administrator, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(3, "Korisnik ne postoji u bazi"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(4, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/jmbg")
	public ResponseEntity<?> getAdministratorByJmbg(@RequestParam String jmbg) {
		try {
			List<Administrator> administratori = adminRepository.findAdministratoriByJmbgStartingWith(jmbg);
			return new ResponseEntity<List<Administrator>>(administratori, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(4, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteAdministrator(@PathVariable Integer id) {
		try {
			if (adminRepository.exists(id)) {
				Administrator administrator = adminRepository.findOne(id);
				adminRepository.delete(administrator);
				return new ResponseEntity<Administrator>(administrator, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(3, "Korisnik ne postoji u bazi"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(4, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> modifyAdministrator(@PathVariable Integer id, @RequestBody OsobaDTO adminDTO) {
		try {
			if (adminRepository.exists(id)) {
				Administrator admin = adminRepository.findOne(id);
				if(adminDTO.getTipKorisnika() != null) {
					if(adminDTO.getTipKorisnika().equals(TipKorisnika.ADMINISTRATOR)) {
						admin.setTipKorisnika(adminDTO.getTipKorisnika());
					}else{return new ResponseEntity<RESTError>(new RESTError(2, "Pogresan tip korisnika"), HttpStatus.BAD_REQUEST);
				}
				}
				if (adminDTO.getIme() != null) {
					admin.setIme(adminDTO.getIme());
				}
				if (adminDTO.getPrezime() != null) {
					admin.setPrezime(adminDTO.getPrezime());
				}
				if (adminDTO.getJmbg() != null) {
					admin.setJmbg(adminDTO.getJmbg());
				}
				if (adminDTO.getAdresa() != null) {
					admin.setAdresa(adminDTO.getAdresa());
				}
				if (adminDTO.getDatumRodjenja() != null) {
					admin.setDatumRodjenja(adminDTO.getDatumRodjenja());
				}
				if (adminDTO.getEmail() != null) {
					admin.setEmail(adminDTO.getEmail());
				}
				adminRepository.save(admin);
				return new ResponseEntity<Administrator>(admin, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(3, "Korisnik ne postoji u bazi"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(4, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
