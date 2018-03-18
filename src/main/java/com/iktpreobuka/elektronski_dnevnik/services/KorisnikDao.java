package com.iktpreobuka.elektronski_dnevnik.services;

import com.iktpreobuka.elektronski_dnevnik.entities.Korisnik;
import com.iktpreobuka.elektronski_dnevnik.entities.dto.KorisnikDTO;

public interface KorisnikDao {

	Korisnik findKorisnikByEmail(String email);

	void createKorisnikAccount(KorisnikDTO korisnik);

}
