package com.users.services;

import java.util.List;

import com.users.entities.Project;
import com.users.entities.User;
import com.users.form.ProjectForm;

public interface ProjectService {
	Project save(ProjectForm projectForm);

	List<Project> findAll();

	Project findById(long id);

	Project update(ProjectForm projectForm, long id);

	void deleteProject(long id);

	Project assignProject(long id, List<User> users);

	List<Project> findNotDone();
}
