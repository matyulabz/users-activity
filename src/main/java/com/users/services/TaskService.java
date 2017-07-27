package com.users.services;

import java.util.List;

import com.users.entities.Task;
import com.users.form.TaskForm;

public interface TaskService {
	
	Task save(TaskForm taskForm);
	
	List<Task> findAll();
	
	Task findById(long id);

	Task update(TaskForm taskForm, long id);
	
	void deleteTask(long id);
	
	Task assignTask(long id, long userId);
	
	Task toggleTaskDone(long id, boolean done);
	
}
