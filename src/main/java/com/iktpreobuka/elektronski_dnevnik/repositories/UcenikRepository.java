package com.iktpreobuka.elektronski_dnevnik.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.iktpreobuka.elektronski_dnevnik.entities.Ucenik;

public interface UcenikRepository extends PagingAndSortingRepository<Ucenik, Integer> {

	Ucenik findByJmbg(String jmbg);
	
	Ucenik findUcenikById(Integer id);
}
