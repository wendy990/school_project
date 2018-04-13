package com.iktpreobuka.elektronski_dnevnik.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.iktpreobuka.elektronski_dnevnik.entities.Role;

public interface RoleRepository extends PagingAndSortingRepository<Role, Integer> {
	
	Role findByRole(String role);

}
