package com.iktpreobuka.elektronski_dnevnik.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.elektronski_dnevnik.entities.Role;
import com.iktpreobuka.elektronski_dnevnik.entities.dto.KorisnikDTO;
import com.iktpreobuka.elektronski_dnevnik.repositories.RoleRepository;

@RestController
@RequestMapping("api/v1/role")
public class RoleController {

	@Autowired
	private RoleRepository roleRepository;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createRole(@RequestBody KorisnikDTO role) {
		if (roleRepository.findByRole(role.getRole()) == null) {
			Role role1 = new Role();
			role1.setRole(role.getRole());
			roleRepository.save(role1);
			return new ResponseEntity<Role>(role1, HttpStatus.OK);
		}
		return new ResponseEntity<RESTError>(new RESTError(1, "Rola vec postoji"), HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getRole(Pageable pageable) {
		return new ResponseEntity<Page<Role>>(roleRepository.findAll(pageable), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getRoleById(@PathVariable Integer id) {
		try {
			if (roleRepository.exists(id)) {
				Role role = roleRepository.findOne(id);
				return new ResponseEntity<Role>(role, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(2, "Rola ne postoji u bazi"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(3, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteRole(@PathVariable Integer id) {
		try {
			if (roleRepository.exists(id)) {
				Role role = roleRepository.findOne(id);
				roleRepository.delete(role);
				return new ResponseEntity<Role>(role, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(2, "Rola ne postoji u bazi"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(3, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> modifyRoleById(@PathVariable Integer id, @RequestBody KorisnikDTO role) {
		try {
			if (roleRepository.exists(id)) {
				Role role1 = roleRepository.findOne(id);
				if (!role.getRole().isEmpty()) {
					role1.setRole(role.getRole());
				}
				roleRepository.save(role1);
				return new ResponseEntity<Role>(role1, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(2, "Rola ne postoji u bazi"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(3, "Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}