package com.iktpreobuka.elektronski_dnevnik.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.repositories.AdminRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.NastavnikRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.RoditeljRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.UcenikRepository;

@Service
public class KorisnikDaoImpl implements KorisnikDao {
	
	/*@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private RoleRepository roleRepository;
		
	/*@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;*/
	
	@Autowired
	private UcenikRepository ucenikRepository;
	
	@Autowired
	private NastavnikRepository nastavnikRepository;
	
	@Autowired
	private RoditeljRepository roditeljRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<String>findAllEmails(){
		String sql1 = "select email from Ucenik ";
		String sql2 = "select email from Roditelj ";
		String sql3 = "select email from Nastavnik ";
		String sql4 = "select email from Administrator ";
		Query query1 = em.createQuery(sql1);
		Query query2 = em.createQuery(sql2);
		Query query3 = em.createQuery(sql3);
		Query query4 = em.createQuery(sql4);
		List<String>result1 = new ArrayList<>();
		result1 = query1.getResultList();
		List<String>result2 = new ArrayList<>();
		result2 = query2.getResultList();
		List<String>result3 = new ArrayList<>();
		result3 = query3.getResultList();
		List<String>result4 = new ArrayList<>();
		result4 = query4.getResultList();
		List<String>results = new ArrayList<>();
		results.addAll(result1);
		results.addAll(result2);
		results.addAll(result3);
		results.addAll(result4);
		return results;
	}
	
	
	@Override
	public List<String> findPasswordByUsername(String email) {
		
		String sql = "select password from Korisnik k where k.email = :email ";
		
		Query query = em.createQuery(sql);
		query.setParameter("email", email);
		
		List<String>result = new ArrayList<>();
		result = query.getResultList();
		return result;
	}
		
	
	/*@Override
	public List<String> findAllEmails() {
		
		String sql = "select u, n, r, a from Ucenik u, Nastavnik n, Roditelj r, Administrator a where email = :email "; 
		
		
		Query query = em.createQuery(sql);
		query.setParameter("email", email);
		
		List<String>result = new ArrayList<>();
		result = query.getResultList();
		return result;
	}*/
	
	/*@Override
	public List<?> findAllEmails(String email) {
		
		String sql = "select email from ( select email from Ucenik union all select email from Nastavnik union all select email from Roditelj union all select email from Administrator ) a where email = :email "; 
		
		
		Query query = em.createQuery(sql);
		query.setParameter("email", email);
		
		List<String>result = new ArrayList<>();
		result = query.getResultList();
		return result;
	}*/
	
	/*@Override
	public List<Ucenik> findUcenikByEmail(String email) {
		
		String sql = "select u from Ucenik u where u.email = :email ";
		
		Query query = em.createQuery(sql);
		query.setParameter("email", email);
		
		List<Ucenik>result = new ArrayList<>();
		result = query.getResultList();
		return result;
	}
	
	@Override
	public List<Nastavnik> findNastavnikByEmail(String email) {
		
		String sql = "select n from Nastavnik n where n.email = :email ";
		
		Query query = em.createQuery(sql);
		query.setParameter("email", email);
		
		List<Nastavnik>result = new ArrayList<>();
		result = query.getResultList();
		return result;
	}
	
	@Override
	public List<Roditelj> findRoditeljByEmail(String email) {
		
		String sql = "select r from Roditelj r where r.email = :email ";
		
		Query query = em.createQuery(sql);
		query.setParameter("email", email);
		
		List<Roditelj>result = new ArrayList<>();
		result = query.getResultList();
		return result;
	}
	
	@Override
	public List<Administrator> findAdministratorByEmail(String email) {
		
		String sql = "select a from Administrator a where a.email = :email ";
		
		Query query = em.createQuery(sql);
		query.setParameter("email", email);
		
		List<Administrator>result = new ArrayList<>();
		result = query.getResultList();
		return result;
	}*/
}
