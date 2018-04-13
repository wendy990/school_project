package com.iktpreobuka.elektronski_dnevnik.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.iktpreobuka.elektronski_dnevnik.entities.Ucenik;

public interface UcenikRepository extends PagingAndSortingRepository<Ucenik, Integer> {

	Ucenik findByJmbg(String jmbg);
	
	Ucenik findUcenikByEmail(String email);
	
	List<Ucenik> findUceniciByJmbgStartingWith(String jmbg);
}
