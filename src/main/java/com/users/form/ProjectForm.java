package com.users.form;

import java.util.List;

import com.users.entities.Company;
import com.users.entities.User;

public class ProjectForm {
	private String name;
	private List<User> users;
	private long dateCreated;
	private long dateStart;
	private long dateEnd;
	private Company company;
//	@DateTimeFormat(pattern="mm/dd/yyyy")
//	private LocalDate date;

	
	public String getName() {
		return name;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public long getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(long dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public long getDateStart() {
		return dateStart;
	}

	public void setDateStart(long dateStart) {
		this.dateStart = dateStart;
	}

	public long getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(long dateEnd) {
		this.dateEnd = dateEnd;
	}

	@Override
	public String toString() {
		return "ProjectForm [name=" + name + ", users=" + users + "]";
	}

}
