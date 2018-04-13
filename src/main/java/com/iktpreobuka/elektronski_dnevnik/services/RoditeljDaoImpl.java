package com.iktpreobuka.elektronski_dnevnik.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.entities.Roditelj;

@Service
public class RoditeljDaoImpl implements RoditeljDao {
	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<Roditelj> findRoditeljiByOdeljenjeId(Integer odeljenjeId) {
		
		String sql = "select distinct r from Roditelj r left join r.deca d, Odeljenje o left join o.ucenici u where d.id = u.id "
				+ "and o.id = :odeljenjeId";

		Query query = em.createQuery(sql);
		query.setParameter("odeljenjeId", odeljenjeId);

		List<Roditelj> result = new ArrayList<>();
		result = query.getResultList();
		return result;
	}

}
