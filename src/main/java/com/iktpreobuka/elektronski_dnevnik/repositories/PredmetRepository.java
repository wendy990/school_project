package com.iktpreobuka.elektronski_dnevnik.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.iktpreobuka.elektronski_dnevnik.entities.Predmet;

public interface PredmetRepository extends PagingAndSortingRepository<Predmet, Integer> {
	
	List<Predmet> findPredmetiByNazivStartingWith(String naziv);
	
	Predmet findPredmetByNaziv(String naziv);
	
	Predmet findPredmetById(Integer id);

}
