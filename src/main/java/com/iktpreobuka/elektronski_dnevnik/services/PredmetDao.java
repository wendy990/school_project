package com.iktpreobuka.elektronski_dnevnik.services;

import java.util.List;

import com.iktpreobuka.elektronski_dnevnik.entities.Predmet;
import com.iktpreobuka.elektronski_dnevnik.entities.Ucenik;

public interface PredmetDao {
	
	public List<Predmet>findPredmetByUcenikId(Integer ucenikid);
	
	public List<Predmet>findPredmetByNastavnikId(Integer nastavnikid);
	
	public List<Predmet>findPredmetByOdeljenjeId(Integer odeljenjeId);
	
	public List<Ucenik>findUceniciByPredmetId(Integer predmetId);
	
	public List<Predmet>findPredmetByNastavnikIdAndOdeljenjeId(Integer nastavnikId, Integer odeljenjeId);
	
	public List<Predmet>findPredmetByNastavnikAndUcenik(Integer nastavnikId, Integer ucenikId);

}
