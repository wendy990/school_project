package com.iktpreobuka.elektronski_dnevnik.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.iktpreobuka.elektronski_dnevnik.entities.Roditelj;

public interface RoditeljRepository extends PagingAndSortingRepository<Roditelj, Integer> {
	
	Roditelj findRoditeljByJmbg(String jmbg);

}
