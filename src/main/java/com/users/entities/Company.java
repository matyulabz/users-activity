package com.users.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class Company implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7078852694527019203L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "company_id")
	private long companyId;

	@Version
	private int version;

	private String name;

	private String address;
	private Location location;

	@OneToMany(mappedBy = "company")
	private List<User> users = new ArrayList<>();
	@OneToMany(mappedBy = "company")
	private List<Project> projects = new ArrayList<>();
	private long dateCreated;

	public List<User> getUsers() {
		if (users == null) {
			users = new ArrayList<>();
		}
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Company() {

	}

	public Company(String name, String address) {
		this.name = name;
		this.address = address;
		this.dateCreated = new Date().getTime();
	}

	public List<Project> getProjects() {
		if (projects == null) {
			projects = new ArrayList<>();
		}
		return projects;
	}

	public Location getLocation() {
		if (location == null) {
			location = new Location();
		}
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
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

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
