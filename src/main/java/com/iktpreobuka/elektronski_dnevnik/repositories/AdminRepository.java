package com.iktpreobuka.elektronski_dnevnik.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.iktpreobuka.elektronski_dnevnik.entities.Administrator;

public interface AdminRepository extends PagingAndSortingRepository<Administrator, Integer> {

	Administrator findByJmbg(String jmbg);
	
	List<Administrator> findAdministratoriByJmbgStartingWith(String jmbg);

	Administrator findAdministratorByEmail(String email);
}
