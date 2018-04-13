/*package com.iktpreobuka.elektronski_dnevnik.controllers;

import java.util.List;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.elektronski_dnevnik.entities.Administrator;
import com.iktpreobuka.elektronski_dnevnik.entities.Korisnik;
import com.iktpreobuka.elektronski_dnevnik.entities.Nastavnik;
import com.iktpreobuka.elektronski_dnevnik.entities.Roditelj;
import com.iktpreobuka.elektronski_dnevnik.entities.Role;
import com.iktpreobuka.elektronski_dnevnik.entities.Ucenik;
import com.iktpreobuka.elektronski_dnevnik.entities.dto.KorisnikDTO;
import com.iktpreobuka.elektronski_dnevnik.repositories.AdminRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.KorisnikRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.NastavnikRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.RoditeljRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.RoleRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.UcenikRepository;
import com.iktpreobuka.elektronski_dnevnik.services.KorisnikDao;

@RestController
@RequestMapping(path = "api/v1/login")
public class LoginController {
	
	//register i login se obicno izdvoje da postoji poseban logincontroller
	//u njemu pravim register korisnika i login
	
	//register: da li unet mail vec postoji (pronadji korisnika po mail-u koji citas iz baze)
	//login: da li postoji email, ako postoji da li je lozinka dobra, ako je sve dobro, autorizacija

	    //private final int MAX_ATTEMPT = 3;

		@Autowired
		private KorisnikRepository korisnikRepository;
		
		@Autowired
		private UcenikRepository ucenikRepository;
		
		@Autowired
		private NastavnikRepository nastavnikRepository;
		
		@Autowired
		private RoditeljRepository roditeljRepository;
		
		@Autowired
		private AdminRepository adminRepository;

		@Autowired
		private RoleRepository roleRepository;

		@Autowired
		private KorisnikDao korisnikDao;

		@Autowired 
		private BCryptPasswordEncoder bCryptPasswordEncoder;
		
		@RequestMapping(method = RequestMethod.POST)
	    public ResponseEntity<?> createKorisnik(@RequestBody KorisnikDTO korisnikDTO, @RequestParam Integer roleId) {
			if (korisnikRepository.findByEmail(korisnikDTO.getEmail()) == null) {
				Korisnik korisnik = new Korisnik();
		     	korisnik.setEmail(korisnikDTO.getEmail());
		    	korisnik.setPassword(korisnikDTO.getPassword());
			    Role role = roleRepository.findOne(roleId);
		 	    korisnik.setRole(role);
			    korisnikRepository.save(korisnik);
			 return new ResponseEntity<Korisnik>(korisnik, HttpStatus.OK);
		     }
		     return new ResponseEntity<RESTError>(new RESTError(1, "Korisnik vec postoji"), HttpStatus.BAD_REQUEST);
	    }

		@RequestMapping(method = RequestMethod.POST)
		public ResponseEntity<?> registerKorisnik(@RequestBody KorisnikDTO korisnikDTO){
			if(korisnikDao.findAllEmails().contains(korisnikDTO.getEmail())) {
				if(ucenikRepository.findUcenikByEmail(korisnikDTO.getEmail()) != null) {
					   Ucenik ucenik = ucenikRepository.findUcenikByEmail(korisnikDTO.getEmail());
					   Korisnik korisnik = new Korisnik();
				       korisnik.setEmail(korisnikDTO.getEmail());
				       korisnik.setPassword(bCryptPasswordEncoder.encode(korisnikDTO.getPassword()));
				       Role role = roleRepository.findByRole("ucenik");
			       	   korisnik.setRole(role);
			       	   ucenik.setKorisnik(korisnik);
				       korisnikRepository.save(korisnik);
				       ucenikRepository.save(ucenik);
				       return new ResponseEntity<Korisnik>(korisnik, HttpStatus.OK);			
				}
				else if(roditeljRepository.findRoditeljByEmail(korisnikDTO.getEmail()) != null) {
				   Roditelj roditelj = roditeljRepository.findRoditeljByEmail(korisnikDTO.getEmail());
				   Korisnik korisnik = new Korisnik();
			       korisnik.setEmail(korisnikDTO.getEmail());
		       	   korisnik.setPassword(bCryptPasswordEncoder.encode(korisnikDTO.getPassword()));
		       	   Role role = roleRepository.findByRole("roditelj");
		       	   korisnik.setRole(role);
		       	   roditelj.setKorisnik(korisnik);
			       korisnikRepository.save(korisnik);
			       roditeljRepository.save(roditelj);
			       return new ResponseEntity<Korisnik>(korisnik, HttpStatus.OK);
			    }
				else if(nastavnikRepository.findNastavnikByEmail(korisnikDTO.getEmail()) != null) {
					   Nastavnik nastavnik = nastavnikRepository.findNastavnikByEmail(korisnikDTO.getEmail());
					   Korisnik korisnik = new Korisnik();
				       korisnik.setEmail(korisnikDTO.getEmail());
				       korisnik.setPassword(korisnikDTO.getPassword());
				       korisnik.setPassword(bCryptPasswordEncoder.encode(korisnikDTO.getPassword()));
			           Role role = roleRepository.findByRole("nastavnik");
			       	   korisnik.setRole(role);
			       	   nastavnik.setKorisnik(korisnik);
				       korisnikRepository.save(korisnik);
				       nastavnikRepository.save(nastavnik);
				       return new ResponseEntity<Korisnik>(korisnik, HttpStatus.OK);
				    }
				else if (adminRepository.findAdministratorByEmail(korisnikDTO.getEmail()) != null) {
					   Administrator admin = adminRepository.findAdministratorByEmail(korisnikDTO.getEmail());
					   Korisnik korisnik = new Korisnik();
				       korisnik.setEmail(korisnikDTO.getEmail());
				       korisnik.setPassword(korisnikDTO.getPassword());
				       korisnik.setPassword(bCryptPasswordEncoder.encode(korisnikDTO.getPassword()));
			       	   Role role = roleRepository.findByRole("administrator");
			       	   korisnik.setRole(role);
			       	   admin.setKorisnik(korisnik);
				       korisnikRepository.save(korisnik);
				       adminRepository.save(admin);
				       return new ResponseEntity<Korisnik>(korisnik, HttpStatus.OK);
				    }
		}return new ResponseEntity<RESTError>(new RESTError(1, "Korisnik ne postoji u bazi"), HttpStatus.NOT_FOUND);
		}
		
		@RequestMapping(method= RequestMethod.GET)
		public ResponseEntity<?> loginKorisnik(@RequestParam String email, @RequestParam String password){
			try {
				if(korisnikRepository.findByEmail(email) != null) {
					Korisnik korisnik = korisnikRepository.findByEmail(email);
					//if(new BCryptPasswordEncoder().matches(password, StringUtils.join(korisnikDao.findPasswordByUsername(email)))) {
					//if(new BCryptPasswordEncoder().matches(password, korisnikDao.findPasswordByUsername(email).toString())){
      				if(bCryptPasswordEncoder.matches(password, StringUtils.join(korisnikDao.findPasswordByUsername(email)))) {
						return new ResponseEntity<Korisnik>(korisnik, HttpStatus.ACCEPTED);
					} return new ResponseEntity<RESTError>(new RESTError(2, "Pogresna lozinka"), HttpStatus.FORBIDDEN);
				}return new ResponseEntity<RESTError>(new RESTError(1, "Korisnik ne postoji u bazi"), HttpStatus.NOT_FOUND);
			}catch(Exception e) {
				return new ResponseEntity<RESTError>(new RESTError(3, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		
		@RequestMapping(method = RequestMethod.GET, value = "/pass")
		public List<String> findPassword(@RequestParam String email){
			return korisnikDao.findPasswordByUsername(email);
		}
		
		@RequestMapping(method = RequestMethod.GET, value = "/email")
		public List<String>findAllEmails() {
			return korisnikDao.findAllEmails();
		}
		
		
	}*/
