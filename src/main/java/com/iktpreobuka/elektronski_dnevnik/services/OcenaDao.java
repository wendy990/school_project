package com.iktpreobuka.elektronski_dnevnik.services;

import java.util.List;

import com.iktpreobuka.elektronski_dnevnik.entities.Ocena;

public interface OcenaDao {
	
	//Ocena removeOcenaById(Integer id); SOFTDELETE
	
	List<Ocena>findOceneByUcenikId(Integer predmetId, Integer ucenikId);
	
	List<Ocena>findOceneByPolugodiste1(Integer predmetId, Integer ucenikId);
	
	List<Ocena>findOceneByPolugodiste2(Integer predmetId, Integer ucenikId);
	
	List<Ocena> findZakljucnaOcenaPolugodiste1(Integer predmetId, Integer ucenikId);

}
