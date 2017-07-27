package com.users.services;

import java.sql.Blob;
import java.util.List;

import com.users.entities.Company;
import com.users.entities.Project;
import com.users.entities.User;
import com.users.form.UserForm;

public interface UserService {

	User save(UserForm userForm);

	List<User> findAll();

	User findById(long id);

	User update(UserForm userForm, long id);

	void deleteUser(long id);

	List<User> findByCompany(Company company);
	
	List<User> findInProject(List<User> users, Project project);
	List<User> findNotInProject(List<User> users, Project project);
	
	User findByUsername(String username);
	
	User updatePicture(byte[] photo, long userId);
}
