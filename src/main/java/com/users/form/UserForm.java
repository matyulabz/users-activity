package com.users.form;

import java.util.List;
import java.util.Set;

import com.users.entities.Company;
import com.users.entities.Project;
import com.users.entities.Role;
import com.users.entities.Task;

public class UserForm {

	private String username;
	private String password;
	private String address;
	private String firstName;
	private String lastName;
	private Company company;
	private String phoneNumber;
	private Set<Task> tasks;
	private List<Project> projects;
	private long dateCreated;
	private Set<Role> role;
	private String encryptedPassword;
	private LocationForm location;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public long getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(long dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocationForm getLocation() {
		return location;
	}

	public void setLocation(LocationForm location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "UserForm [username=" + username + ", address=" + address + ", firstName=" + firstName + ", lastName="
				+ lastName + ", company=" + company + ", phoneNumber=" + phoneNumber + ", dateCreated=" + dateCreated
				+ ", role=" + role + ", location=" + location + "]";
	}
}
