package com.users.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.users.entities.Project;
import com.users.entities.User;
import com.users.form.ProjectForm;
import com.users.repositories.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public Project save(ProjectForm projectForm) {
		Project project = new Project(projectForm.getName(), projectForm.getDateStart(), 
				projectForm.getDateEnd(),projectForm.getCompany());
		Project savedProj = projectRepository.save(project);
		return savedProj;
	}

	@Override
	public List<Project> findAll() {
		return projectRepository.findAll();
	}

	@Override
	public Project findById(long id) {
		Project project = projectRepository.findOne(id);
		if (project == null) {
			return null;
		}
		return project;
	}

	@Override
	public Project update(ProjectForm projectForm, long id) {
		Project project = projectRepository.findOne(id);
		if (project == null) {
			return null;
		}
		project.setName(projectForm.getName());
		project.setDateStart(projectForm.getDateStart());
		project.setDateEnd(projectForm.getDateEnd());
		project.setCompany(projectForm.getCompany());
		Project newProject = projectRepository.save(project);
		return newProject;
	}

	@Override
	public void deleteProject(long id) {
		projectRepository.delete(id);
	}

	@Override
	public Project assignProject(long id, List<User> users) {
		Project project = projectRepository.findOne(id);
		if (project == null) {
			return null;
		}
		users.stream().forEach(u -> project.getUsers().add(u));
		Project newProject = projectRepository.save(project);
		return newProject;
	}

	@Override
	public List<Project> findNotDone() {
		return projectRepository.findByDoneFalse();
	}
}
