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
	
	/*@PersistenceContext
	EntityManager em;
	
	@Override
	public List<Ucenik> findUcenikByRoditeljId(Integer roditeljId){
		
		String sql = "select u " + "from Ucenik u " + "left join fetch u.roditelji r " + "where r.id = :roditeljId ";
 
		Query query = em.createQuery(sql);
		query.setParameter("roditeljId", roditeljId);
		
		List<Ucenik> result = new ArrayList<>();
		result = query.getResultList();
		return result;
	}
*/
}
