package com.users.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.users.entities.Company;
import com.users.form.CompanyForm;
import com.users.repositories.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public Company save(CompanyForm companyForm) {
		Company company = new Company(companyForm.getName(), companyForm.getAddress());
		company.getLocation().setLatitude(companyForm.getLocation().getLatitude());
		company.getLocation().setLongitude(companyForm.getLocation().getLongitude());
		Company savedCompany = companyRepository.save(company);
		return savedCompany;
	}

	@Override
	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	@Override
	public Company findById(long id) {
		Company company = companyRepository.findOne(id);
		if (company == null) {
			return null;
		}
		return company;
	}

	@Override
	public Company update(CompanyForm companyForm, long id) {
		Company company = companyRepository.findOne(id);
		if (company == null) {
			return null;
		}
		company.setName(companyForm.getName());
		company.setAddress(companyForm.getAddress());
		Company updatedCompany = companyRepository.save(company);
		return updatedCompany;
	}

	@Override
	public void deleteCompany(long id) {
		companyRepository.delete(id);
	}

}
