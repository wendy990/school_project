package com.iktpreobuka.elektronski_dnevnik.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.entities.Predmet;
import com.iktpreobuka.elektronski_dnevnik.entities.Ucenik;

@Service
public class PredmetDaoImpl implements PredmetDao{
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<Predmet>findPredmetByUcenikId(Integer ucenikId){
		
		String sql = "select distinct(p) from Predmet p, Ucenik u, Predaje pr " +
		"where u.odeljenje.id = pr.odeljenjeId and p.id = pr.predmetId and u.id = :ucenikId ";
		
		Query query = em.createQuery(sql);
		query.setParameter("ucenikId", ucenikId);
		query.setFirstResult(0);
		query.setMaxResults(5);
		
		List<Predmet>result = new ArrayList<>();
		result = query.getResultList();
		return result;
	}
	
	@Override
	public List<Predmet>findPredmetByNastavnikId(Integer nastavnikId){
		
		String sql = "select distinct(p) from Predmet p, Predaje pr " +
		"where p.id = pr.predmetId and pr.nastavnikId = :nastavnikId ";
		
		Query query = em.createQuery(sql);
		query.setParameter("nastavnikId", nastavnikId);
		query.setFirstResult(0);
		query.setMaxResults(5);
		
		List<Predmet>result = new ArrayList<>();
		result = query.getResultList();
		return result;
	}
	
	
	@Override
	public List<Predmet>findPredmetByOdeljenjeId(Integer odeljenjeId){
		
		String sql = "select distinct(p) from Predmet p, Predaje pr " +
				"where p.id = pr.predmetId and pr.odeljenjeId = :odeljenjeId ";
		
		Query query = em.createQuery(sql);
		query.setParameter("odeljenjeId", odeljenjeId);
		query.setFirstResult(0);
		query.setMaxResults(5);
		
		List<Predmet>result = new ArrayList<>();
		result = query.getResultList();
		return result;
	}

}
