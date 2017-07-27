package com.users.controller;

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
import com.users.form.TaskForm;
import com.users.services.ProjectService;
import com.users.services.TaskService;
import com.users.services.UserService;


@Controller
public class TaskController {
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProjectService projectService;

	@RequestMapping(value = "/task/list", method = RequestMethod.GET)
	public String index(Model model) {
		List<Task> tasks = taskService.findAll();
		model.addAttribute("tasks", tasks);
		return "/task/index";
	}
	
	@RequestMapping(value = "/task/add", method = RequestMethod.GET)
	public String newTask(Model model) {
		List<Project> projects = projectService.findNotDone();
		List<User> users = userService.findAll();
		model.addAttribute("task", new TaskForm());
		model.addAttribute("projects", projects);
		model.addAttribute("users", users);
		return "/task/add";
	}

	@RequestMapping(value = "/task/add", method = RequestMethod.POST)
	public String addNewTask(@ModelAttribute("task") @Valid TaskForm taskForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("task", taskForm);
			return "task";
		}
		taskService.save(taskForm);
		return "redirect:/task/list";
	}
	
	@RequestMapping("/task/details/{id}")
	public String viewTask(@PathVariable long id, Model model) {
		Task oneTask = taskService.findById(id);
		Project proj = oneTask.getProject();
		if (oneTask == null || proj == null) {
			return "errorPage";
		}
		List<User> usersInCompany = userService.findByCompany(proj.getCompany());
		List<User> unassignedUsers = userService.findInProject(usersInCompany, proj);
		model.addAttribute("users", unassignedUsers);
		
		model.addAttribute("task", oneTask);
		return "/task/taskDetails";
	}
	
	@RequestMapping("/task/assign")
	public String assignTask(@RequestParam long taskId, @RequestParam long userId, Model model) {
		Task oneTask = taskService.findById(taskId);
		if (oneTask == null) {
			return "errorPage";
		}
		taskService.assignTask(taskId, userId);
		return "redirect:/task/details/" + taskId;
	}
	
	@RequestMapping("/task/done")
	public String toggleTaskDone(@RequestParam long taskId, @RequestParam boolean done, Model model) {
		Task oneTask = taskService.findById(taskId);
		if (oneTask == null) {
			return "errorPage";
		}
		taskService.toggleTaskDone(taskId, done);
		return "redirect:/task/details/" + taskId;
	}

	@RequestMapping("/task/delete/{id}")
	public String deleteTask(@PathVariable long id, Model model) {
		Task oneTask = taskService.findById(id);
		if (oneTask == null) {
			return "errorPage";
		}
		taskService.deleteTask(id);
		return "redirect:/task/list";
	}
}
