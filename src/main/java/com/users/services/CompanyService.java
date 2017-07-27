package com.users.services;

import java.util.List;

import com.users.entities.Company;
import com.users.form.CompanyForm;

public interface CompanyService {
	
	Company save(CompanyForm companyForm);
	
	List<Company> findAll();
	
	Company findById(long id);

	Company update(CompanyForm companyForm, long id);
	
	void deleteCompany(long id);

}
