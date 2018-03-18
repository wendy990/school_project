package com.iktpreobuka.elektronski_dnevnik.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.entities.Korisnik;
import com.iktpreobuka.elektronski_dnevnik.repositories.KorisnikRepository;

@Service
public class KorisnikDaoImpl {
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	/*@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;*/

	// update User service to include password encoding before saving new password in database
	
	/*public void save(Korisnik korisnik) {
		korisnik.setPassword(bCryptPasswordEncoder.encode(korisnik.getPassword()));
		korisnikRepository.save(korisnik);
	}*/
	
	public Korisnik findByEmail(String email){
		return korisnikRepository.findByEmail(email);
	}
	
	
}
