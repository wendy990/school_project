package com.iktpreobuka.elektronski_dnevnik.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.entities.Odeljenje;
import com.iktpreobuka.elektronski_dnevnik.entities.Ucenik;

@Service
public class OdeljenjeDaoImpl implements OdeljenjeDao{
	
	@PersistenceContext
	EntityManager em;
	
	
	@Override
	public List<Odeljenje> findOdeljenjeByNastavnikId(Integer nastavnikId) {

		String sql = "select distinct(o) from Odeljenje o, Predaje pr " +
		"where o.id = pr.odeljenjeId and pr.nastavnikId = :nastavnikId ";

		Query query = em.createQuery(sql);
		query.setParameter("nastavnikId", nastavnikId);
		/*query.setFirstResult(0);
		query.setMaxResults(5);*/

		List<Odeljenje> result = new ArrayList<>();
		result = query.getResultList();
		return result;
	}
}