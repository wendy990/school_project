package com.iktpreobuka.elektronski_dnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.elektronski_dnevnik.entities.Odeljenje;

public interface OdeljenjeRepository extends CrudRepository<Odeljenje, Integer>{
	
	Odeljenje findByOznaka(String oznaka);

}
