package com.users.form;

public class CompanyForm {

	private String name;
	private String address;
	private LocationForm location;
	private long dateCreated;

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

	public LocationForm getLocation() {
		return location;
	}

	public void setLocation(LocationForm location) {
		this.location = location;
	}

	public long getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(long dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return "CompanyForm [name=" + name + ", address=" + address + ", location=" + location + ", dateCreated="
				+ dateCreated + "]";
	}
}
