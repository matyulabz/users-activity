package com.users;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.users.entities.Company;
import com.users.entities.Project;
import com.users.entities.Task;
import com.users.entities.User;
import com.users.repositories.CompanyRepository;
import com.users.repositories.ProjectRepository;
import com.users.repositories.TaskRepository;
import com.users.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersActivityApplicationTests {

	@Test
	@Ignore
	public void contextLoads() {
	}

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private ProjectRepository projectRepository;

}
