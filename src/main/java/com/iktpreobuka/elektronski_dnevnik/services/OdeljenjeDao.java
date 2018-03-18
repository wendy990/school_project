package com.iktpreobuka.elektronski_dnevnik.services;

import java.util.List;

import com.iktpreobuka.elektronski_dnevnik.entities.Odeljenje;

public interface OdeljenjeDao {
	
	List<Odeljenje>findOdeljenjeByNastavnikId(Integer nastavnikId);

}
