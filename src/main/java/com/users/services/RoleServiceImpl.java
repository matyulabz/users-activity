package com.users.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.users.entities.Role;
import com.users.form.RoleForm;
import com.users.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role save(RoleForm roleForm) {
		Role role = new Role();
		role.setRole(roleForm.getRole());
		Role savedRole = roleRepository.save(role);
		return savedRole;
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Role findById(long id) {
		return roleRepository.findOne(id);
	}

	@Override
	public Role update(RoleForm roleForm, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRole(long id) {
		// TODO Auto-generated method stub
		
	}

}
