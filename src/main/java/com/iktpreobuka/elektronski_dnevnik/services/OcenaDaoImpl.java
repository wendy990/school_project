package com.iktpreobuka.elektronski_dnevnik.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.entities.Ocena;
import com.iktpreobuka.elektronski_dnevnik.entities.Predmet;

@Service
public class OcenaDaoImpl implements OcenaDao {
	
	@PersistenceContext
	EntityManager em;
	
	/*@Override   SOFTDELETE
	public Ocena removeOcenaById(Integer id){
		Ocena ocena = em.find(Ocena.class, id);
		em.remove(ocena);
		return ocena;
	}*/
	
	@Override
	public List<Ocena>findOceneByUcenikId(Integer predmetId, Integer ucenikId){
		
		String sql = "select o from Ocena o " +
		"where o.predmet.id = :predmetId and o.ucenik.id = :ucenikId and o.deleted = false ";
		
		Query query = em.createQuery(sql);
		query.setParameter("predmetId", predmetId);
		query.setParameter("ucenikId", ucenikId);
		
		List<Ocena>result = new ArrayList<>();
		result = query.getResultList();
		return result;
		
	}
	
	@Override
	public List<Ocena>findOceneByPolugodiste1(Integer predmetId, Integer ucenikId){
		
		String sql = "select o from Ocena o " +
		"where o.predmet.id = :predmetId and o.ucenik.id = :ucenikId and o.deleted = false " +
				"and o.polugodiste = 1 ";
		
		Query query = em.createQuery(sql);
		query.setParameter("predmetId", predmetId);
		query.setParameter("ucenikId", ucenikId);
		
		List<Ocena>result = new ArrayList<>();
		result = query.getResultList();
		return result;
	}
	
	@Override
	public List<Ocena>findOceneByPolugodiste2(Integer predmetId, Integer ucenikId){
		
		String sql = "select o from Ocena o " +
		"where o.predmet.id = :predmetId and o.ucenik.id = :ucenikId and o.deleted = false " +
				"and o.polugodiste = 2 ";
		
		Query query = em.createQuery(sql);
		query.setParameter("predmetId", predmetId);
		query.setParameter("ucenikId", ucenikId);
		
		List<Ocena>result = new ArrayList<>();
		result = query.getResultList();
		return result;
	}
	
	
	@Override
	public List<Ocena> findZakljucnaOcenaPolugodiste1(Integer predmetId, Integer ucenikId){
		
		String sql = "select o from Ocena o " +
		"where o.predmet.id = :predmetId and o.ucenik.id = :ucenikId and o.deleted = false " +
				"and o.polugodiste = 1 and o.tipOcene = 4";
		
		Query query = em.createQuery(sql);
		query.setParameter("predmetId", predmetId);
		query.setParameter("ucenikId", ucenikId);
		
		List<Ocena>result = new ArrayList<>();
		result = query.getResultList();
		return result;
	}
	

}
