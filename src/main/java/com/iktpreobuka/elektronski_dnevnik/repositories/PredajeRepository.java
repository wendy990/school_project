package com.iktpreobuka.elektronski_dnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.elektronski_dnevnik.entities.Predaje;
import com.iktpreobuka.elektronski_dnevnik.entities.PredajePK;

public interface PredajeRepository extends CrudRepository<Predaje, PredajePK>{
	
	Predaje findByNastavnikIdAndPredmetId(Integer nastavnikId, Integer predmetId);

}
