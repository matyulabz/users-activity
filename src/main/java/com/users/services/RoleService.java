package com.users.services;

import java.util.List;

import com.users.entities.Role;
import com.users.form.RoleForm;

public interface RoleService {

	Role save(RoleForm roleForm);

	List<Role> findAll();

	Role findById(long id);

	Role update(RoleForm roleForm, long id);

	void deleteRole(long id);
}
