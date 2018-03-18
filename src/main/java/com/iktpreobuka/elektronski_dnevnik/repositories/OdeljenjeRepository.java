package com.iktpreobuka.elektronski_dnevnik.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.iktpreobuka.elektronski_dnevnik.entities.Odeljenje;

public interface OdeljenjeRepository extends PagingAndSortingRepository<Odeljenje, Integer>{
	
	Odeljenje findByOznaka(String oznaka);

}
