package com.iktpreobuka.elektronski_dnevnik.services;

import java.util.List;

public interface KorisnikDao {

	// ResponseEntity<?> findKorisnikByEmail(String email);

	/*
	 * List<Ucenik>findUcenikByEmail(String email);
	 * 
	 * List<Nastavnik>findNastavnikByEmail(String email);
	 * 
	 * List<Roditelj>findRoditeljByEmail(String email);
	 * 
	 * List<Administrator>findAdministratorByEmail(String email);
	 */

	List<String> findAllEmails();

	//String encodePassword(String password);
	
	List<String> findPasswordByUsername(String email);

	// void registerKorisnik(KorisnikDTO korisnikDTO);

}
