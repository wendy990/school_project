package com.iktpreobuka.elektronski_dnevnik.services;

import java.util.List;

import com.iktpreobuka.elektronski_dnevnik.entities.Ucenik;

public interface UcenikDao {
	
	public List<Ucenik>findUceniciByRoditeljId(Integer roditeljId);
	
	//public List<Ucenik>findUceniciByPredmetId(Integer predmetId);
	
	public List<Ucenik>findUceniciByOdeljenjeId(Integer odeljenjeId);


}
