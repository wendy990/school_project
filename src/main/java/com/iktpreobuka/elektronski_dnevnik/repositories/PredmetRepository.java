package com.iktpreobuka.elektronski_dnevnik.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.iktpreobuka.elektronski_dnevnik.entities.Predmet;

public interface PredmetRepository extends PagingAndSortingRepository<Predmet, Integer> {
	
	Predmet findPredmetByNaziv(String naziv);
	
	Predmet findPredmetById(Integer id);

}
