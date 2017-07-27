package com.users.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;

import com.users.entities.Company;
import com.users.entities.Task;
import com.users.entities.User;
import com.users.form.UserForm;
import com.users.reports.GeneratePdfReport;
import com.users.services.CompanyService;
import com.users.services.RoleService;
import com.users.services.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ApplicationContext appContext;
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public String index(Model model) {
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		return "/user/index";
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.GET)
	public String newUser(Model model) {
		List<Company> companies = companyService.findAll();
		model.addAttribute("user", new UserForm());
		model.addAttribute("companies", companies);
		model.addAttribute("roles", roleService.findAll());
		return "/user/add";
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public String addNewUser(@ModelAttribute("user") @Valid UserForm userForm, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("user", userForm);
			return "newUser";
		}
		userService.save(userForm);
		return "redirect:/user/list";
	}

	@RequestMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable long id, Model model) {
		User oneUser = userService.findById(id);
		if (oneUser == null) {
			return "error";
		}
		userService.deleteUser(id);
		return "redirect:/user/list";
	}

	@RequestMapping("/user/profile/{id}")
	public String viewUser(@PathVariable long id, Model model, Authentication auth) {
		User oneUser = userService.findById(id);
		model.addAttribute("user", oneUser);
		model.addAttribute("loggedInUsername", auth.getName());
		if (oneUser == null) {
			return "error";
		}
		return "/user/profile";
	}

	@RequestMapping(value = "/userTaskReport/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> userTaskReport(@PathVariable long id) throws IOException {

		User user = userService.findById(id);

		ByteArrayInputStream bis = GeneratePdfReport.INSTANCE.userTaskReport(user);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=userTask.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}

	@RequestMapping(value = "/jasper/{type}/{id}", method = RequestMethod.GET)
	public ModelAndView report(@PathVariable("type") String type, @PathVariable("id") long id) {
		User user = userService.findById(id);
		Date dateToday = new Date();
		Set<Task> tasks = user.getTasks();
		JasperReportsMultiFormatView view = new JasperReportsMultiFormatView();
		view.setUrl("classpath:jasperReports/userTaskReport.jasper");
		view.setApplicationContext(appContext);

		Map<String, Object> params = new HashMap<>();
		Properties mappings = new Properties();
		List<HashMap<String, Object>> userTasks = new ArrayList<>();
		for (Task task : tasks) {
			Date dateCreated = new Date(task.getDateCreated());
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String dateC = format.format(dateCreated);
			Date dateDone = new Date(task.getDateDone());
			String dateD = "n/a";
			String doneText = "False";
			if (task.isDone()) {
				doneText = "True";
				dateD = format.format(dateDone);
			}
			HashMap<String, Object> items = new HashMap<>();
			items.put("name", task.getName());
			items.put("project", task.getProject().getName());
			items.put("dateCreated", dateC);
			items.put("done", doneText);
			items.put("dateDone", dateD);
			userTasks.add(items);
		}

		DateFormat fileformat = new SimpleDateFormat("yyyyMMdd");
		String dateF = fileformat.format(dateToday);
		String nameFormat = user.getFirstName().toUpperCase().replaceAll("\\s", "")
				+ user.getLastName().toUpperCase().replaceAll("\\s", "");
		String filename = nameFormat + "-" + dateF + "-tasks." + type;
		mappings.setProperty("html", "inline; filename=" + filename);
		mappings.setProperty("xls", "inline; filename=" + filename);
		mappings.setProperty("pdf", "inline; filename=" + filename);
		mappings.setProperty("csv", "inline; filename=" + filename);
		view.setContentDispositionMappings(mappings);
		params.put("datasource", userTasks);
		params.put("TASK_OWNER", nameFormat);
		params.put("format", type);

		return new ModelAndView(view, params);
	}
}
