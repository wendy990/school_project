package com.iktpreobuka.elektronski_dnevnik.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.entities.Ucenik;

@Service
public class UcenikDaoImpl implements UcenikDao {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<Ucenik> findUceniciByRoditeljId(Integer roditeljId) {

		String sql = "select u from Ucenik u left join fetch u.roditelji r where r.id = :roditeljId ";

		Query query = em.createQuery(sql);
		query.setParameter("roditeljId", roditeljId);
		query.setFirstResult(0);
		query.setMaxResults(5);

		List<Ucenik> result = new ArrayList<>();
		result = query.getResultList();
		return result;
	}

	/*@Override
	public List<Ucenik> findUceniciByPredmetId(Integer predmetId) {

		String sql = "select distinct(u) from Ucenik u, Predmet p " + "where u.odeljenje.razred.id = p.razred.id "
				+ "and p.id = :predmetId ";

		Query query = em.createQuery(sql);
		query.setParameter("predmetId", predmetId);
		query.setFirstResult(0);
		query.setMaxResults(5);

		List<Ucenik> result = new ArrayList<>();
		result = query.getResultList();
		return result;
	}
	*/
	@Override
	public List<Ucenik> findUceniciByOdeljenjeId(Integer odeljenjeId) {

		String sql = "select u from Ucenik u left join fetch u.odeljenje o where o.id = :odeljenjeId ";

		Query query = em.createQuery(sql);
		query.setParameter("odeljenjeId", odeljenjeId);
		query.setFirstResult(0);
		query.setMaxResults(5);

		List<Ucenik> result = new ArrayList<>();
		result = query.getResultList();
		return result;
	}

}
