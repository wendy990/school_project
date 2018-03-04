package com.iktpreobuka.elektronski_dnevnik.services;

import org.springframework.stereotype.Service;

@Service
public class KorisnikDaoImpl implements KorisnikDao {
	
	//LOG-IN KORISNIKA
	    //da li postoji email
	    //da li je lozinka tacna
	    //da li ima pravo pristupa
	
	//REGISTER KORISNIKA
	   //on ce pozvati get User, pa ce pitati findUserByEmail
	   //to su metode repozitorijuma koje ce servis pozvati jednu za drugom
	
	//na kraju, servis vraca kontroleru uspesno registrovan, neuspesno registrovan(i zasto)

}
