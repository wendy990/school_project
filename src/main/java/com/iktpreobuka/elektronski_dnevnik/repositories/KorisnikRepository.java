package com.iktpreobuka.elektronski_dnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.elektronski_dnevnik.entities.Korisnik;

public interface KorisnikRepository extends CrudRepository<Korisnik, Integer> {
	
	//findByUsername;
}
