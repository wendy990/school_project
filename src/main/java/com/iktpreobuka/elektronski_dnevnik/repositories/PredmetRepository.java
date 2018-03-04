package com.iktpreobuka.elektronski_dnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.elektronski_dnevnik.entities.Predmet;

public interface PredmetRepository extends CrudRepository<Predmet, Integer> {
	
	Predmet findPredmetByNaziv(String naziv);
	
	Predmet findPredmetById(Integer id);

}
