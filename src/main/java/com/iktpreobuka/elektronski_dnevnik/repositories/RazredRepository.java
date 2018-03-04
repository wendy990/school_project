package com.iktpreobuka.elektronski_dnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.elektronski_dnevnik.entities.Razred;

public interface RazredRepository extends CrudRepository<Razred, Integer> {
	
	Razred findByRazred(Integer razred);

}
