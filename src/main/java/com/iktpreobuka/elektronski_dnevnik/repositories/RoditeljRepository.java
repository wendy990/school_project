package com.iktpreobuka.elektronski_dnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.elektronski_dnevnik.entities.Roditelj;

public interface RoditeljRepository extends CrudRepository<Roditelj, Integer> {
	
	Roditelj findRoditeljByJmbg(String jmbg);

}
