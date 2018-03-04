package com.iktpreobuka.elektronski_dnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.elektronski_dnevnik.entities.Nastavnik;

public interface NastavnikRepository extends CrudRepository<Nastavnik, Integer> {
	
	Nastavnik findByJmbg(String jmbg);
	
	Nastavnik findNastavnikById(Integer id);

}
