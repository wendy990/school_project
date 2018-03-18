package com.iktpreobuka.elektronski_dnevnik.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.iktpreobuka.elektronski_dnevnik.entities.Razred;

public interface RazredRepository extends PagingAndSortingRepository<Razred, Integer> {
	
	Razred findByRazred(Integer razred);

}
