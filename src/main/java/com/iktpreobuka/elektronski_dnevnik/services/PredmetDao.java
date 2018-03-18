package com.iktpreobuka.elektronski_dnevnik.services;

import java.util.List;

import com.iktpreobuka.elektronski_dnevnik.entities.Predmet;

public interface PredmetDao {
	
	public List<Predmet>findPredmetByUcenikId(Integer ucenikid);
	
	public List<Predmet>findPredmetByNastavnikId(Integer nastavnikid);
	
	public List<Predmet>findPredmetByOdeljenjeId(Integer odeljenjeId);

}
