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

import com.users.entities.Company;
import com.users.entities.User;
import com.users.form.CompanyForm;
import com.users.services.CompanyService;
import com.users.services.UserService;

@Controller
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/company/list", method = RequestMethod.GET)
	public String index(Model model) {
		List<Company> companies = companyService.findAll();
		model.addAttribute("companies", companies);
		return "/company/index";
	}
	
	@RequestMapping(value = "/company/add", method = RequestMethod.GET)
	public String newCompany(Model model) {
		model.addAttribute("company", new CompanyForm());
		return "/company/add";
	}

	@RequestMapping(value = "/company/add", method = RequestMethod.POST)
	public String addNewPost(@ModelAttribute("company") @Valid CompanyForm companyForm,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("company", companyForm);
			return "newCompany";
		}
		companyService.save(companyForm);
		return "redirect:/company/list";
	}

	@RequestMapping("/company/delete/{id}")
	public String deleteCompany(@PathVariable long id, Model model) {
		Company oneCompany = companyService.findById(id);
		if (oneCompany == null) {
			return "errorPage";
		}
		companyService.deleteCompany(id);
		return "redirect:/company/list";
	}
	
	@RequestMapping("/company/details/{id}")
	public String viewCompany(@PathVariable long id, Model model) {
		Company oneCompany = companyService.findById(id);
		if (oneCompany == null) {
			return "errorPage";
		}
		List<User> users = userService.findByCompany(oneCompany);
		model.addAttribute("company", oneCompany);
		model.addAttribute("users", users);
		return "/company/companyDetails";
	}
}
