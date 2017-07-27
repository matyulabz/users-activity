package com.users.form;

import com.users.entities.Project;
import com.users.entities.User;

public class TaskForm {

	private String name;
	private String description;
	private User assignedUser;
	private long dateCreated;
	private long dateDone;
	private Project project;
	
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public User getAssignedUser() {
		return assignedUser;
	}

	public void setAssignedUser(User assignedUser) {
		this.assignedUser = assignedUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(long dateCreated) {
		this.dateCreated = dateCreated;
	}

	public long getDateDone() {
		return dateDone;
	}

	public void setDateDone(long dateDone) {
		this.dateDone = dateDone;
	}

	@Override
	public String toString() {
		return "TaskForm [name=" + name + ", description=" + description + "]";
	}

}
