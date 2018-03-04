package com.iktpreobuka.elektronski_dnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.elektronski_dnevnik.entities.Ucenik;

public interface UcenikRepository extends CrudRepository<Ucenik, Integer> {

	Ucenik findByJmbg(String jmbg);
	
	Ucenik findUcenikById(Integer id);
}
