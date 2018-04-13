package com.iktpreobuka.elektronski_dnevnik.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.iktpreobuka.elektronski_dnevnik.entities.Korisnik;

public interface KorisnikRepository extends PagingAndSortingRepository<Korisnik, Integer> {
	
	//findByUsername;
	Korisnik findByEmail(String email);

}
