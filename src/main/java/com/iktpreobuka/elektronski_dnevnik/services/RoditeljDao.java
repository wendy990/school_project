package com.iktpreobuka.elektronski_dnevnik.services;

import java.util.List;

import com.iktpreobuka.elektronski_dnevnik.entities.Roditelj;

public interface RoditeljDao {
	
	public List<Roditelj>findRoditeljiByOdeljenjeId(Integer odeljenjeId);

}
