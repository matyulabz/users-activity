package com.users.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.users.entities.Project;
import com.users.entities.Task;
import com.users.entities.User;
import com.users.form.ProjectForm;
import com.users.form.TaskForm;
import com.users.services.CompanyService;
import com.users.services.ProjectService;
import com.users.services.UserService;

@Controller
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private CompanyService companyService;

	@RequestMapping(value = "/project/list", method = RequestMethod.GET)
	public String index(Model model) {
		List<Project> projects = projectService.findAll();
		model.addAttribute("projects", projects);
		return "/project/index";
	}

	@RequestMapping(value = "/project/add", method = RequestMethod.GET)
	public String newProject(Model model) {
		model.addAttribute("project", new ProjectForm());
		model.addAttribute("companies", companyService.findAll());
		return "/project/add";
	}

	@RequestMapping(value = "/project/add", method = RequestMethod.POST)
	public String addNewProject(@ModelAttribute("project") @Valid ProjectForm projectForm,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("project", projectForm);
			return "/project/add";
		}
		projectService.save(projectForm);
		return "redirect:/project/list";
	}

	@RequestMapping("/project/delete/{id}")
	public String deleteProject(@PathVariable long id, Model model) {
		Project oneProj = projectService.findById(id);
		if (oneProj == null) {
			return "errorPage";
		}
		projectService.deleteProject(id);
		return "redirect:/project/list";
	}

	@RequestMapping("/project/details/{id}")
	public String viewProject(@PathVariable long id, Model model) {
		Project oneProj = projectService.findById(id);
		if (oneProj == null) {
			return "errorPage";
		}
		List<User> usersInCompany = userService.findByCompany(oneProj.getCompany());
		List<User> unassignedUsers = userService.findNotInProject(usersInCompany, oneProj);
		List<Task> tasks = oneProj.getTasks();
		System.out.println(tasks);
		model.addAttribute("task", new TaskForm());
		model.addAttribute("users", unassignedUsers);
		model.addAttribute("project", oneProj);
		model.addAttribute("tasks", tasks);
		return "/project/projectDetails";
	}

	@RequestMapping("/project/assign")
	public String assignProject(@RequestParam long projectId, @RequestParam(value = "userIds[]") List<Long> userIds,
			Model model) {
		Project oneProj = projectService.findById(projectId);
		List<User> users = new ArrayList<>();
		if (oneProj == null) {
			return "errorPage";
		}
		userIds.stream().forEach(uid -> users.add(userService.findById(uid)));
		projectService.assignProject(projectId, users);
		return "redirect:/project/details/" + projectId;
	}
}
