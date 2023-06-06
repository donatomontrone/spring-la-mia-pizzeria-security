package org.java.demo.pizzeria.auth.service;

import java.util.List;
import java.util.Optional;

import org.java.demo.pizzeria.auth.pojo.Role;
import org.java.demo.pizzeria.auth.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<Role> findAll(){
		return roleRepository.findAll();
	}
	
	public Optional<Role> findById(Integer id) {
		return roleRepository.findById(id);
	}
	
	public Role save(Role role) {
		return roleRepository.save(role);
	}
	
	public void delete(Role role) {
		roleRepository.delete(role);
	}
}
