package com.users.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.users.entities.Task;
import com.users.entities.User;
import com.users.form.TaskForm;
import com.users.repositories.TaskRepository;
import com.users.repositories.UserRepository;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public Task save(TaskForm taskForm) {
		Task task = new Task(taskForm.getName(), taskForm.getDescription(), taskForm.getProject());
		if (taskForm.getAssignedUser() != null) {
			task.setUser(taskForm.getAssignedUser());
		}
		Task savedTask = taskRepository.save(task);
		return savedTask;
	}
	
	public Task assignTask(long id, long userId) {
		Task task = taskRepository.findOne(id);
		User user = userRepository.findOne(userId);
		if (task == null || user == null) {
			return null;
		}
		task.setUser(user);
		Task updatedTask = taskRepository.save(task);
		return updatedTask;
	}
	
	@Override
	public List<Task> findAll() {
		return taskRepository.findAll();
	}

	@Override
	public Task findById(long id) {
		return taskRepository.findOne(id);
	}

	@Override
	public Task update(TaskForm taskForm, long id) {
		Task task = taskRepository.findOne(id);
		if (task == null) {
			return null;
		}
		task.setName(taskForm.getName());
		task.setDescription(taskForm.getDescription());
		task.setProject(taskForm.getProject());
		Task updatedTask = taskRepository.save(task);
		return updatedTask;
	}
	
	@Override
	public void deleteTask(long id) {
		taskRepository.delete(id);
	}

	@Override
	public Task toggleTaskDone(long id, boolean done) {
		Task task = taskRepository.findOne(id);
		if (task == null) {
			return null;
		}
		task.setDone(done);
		task.setDateDone(new Date().getTime());
		Task updatedTask = taskRepository.save(task);
		return updatedTask;
	}

}
