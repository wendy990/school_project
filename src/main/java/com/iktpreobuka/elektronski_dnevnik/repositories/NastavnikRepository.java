package com.iktpreobuka.elektronski_dnevnik.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.iktpreobuka.elektronski_dnevnik.entities.Nastavnik;

public interface NastavnikRepository extends PagingAndSortingRepository<Nastavnik, Integer> {
	
	Nastavnik findByJmbg(String jmbg);
	
	Nastavnik findNastavnikById(Integer id);

}
