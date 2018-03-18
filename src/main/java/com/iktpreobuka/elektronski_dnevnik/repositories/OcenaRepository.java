package com.iktpreobuka.elektronski_dnevnik.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.iktpreobuka.elektronski_dnevnik.entities.Ocena;
import com.iktpreobuka.elektronski_dnevnik.enums.TipOcene;

public interface OcenaRepository extends PagingAndSortingRepository<Ocena, Integer> {
	
	Ocena findOcenaByTipOceneAndPolugodiste(TipOcene tipOcene, Integer polugodiste);

}
