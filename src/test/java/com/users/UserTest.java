package com.users;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.users.entities.Company;
import com.users.entities.User;
import com.users.form.UserForm;
import com.users.services.UserService;

@SpringBootTest(classes = UsersActivityApplication.class)
@RunWith(SpringRunner.class)
public class UserTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	@Ignore
	public void createUserTest(){
//		Company company = new Company("ABC Company", "Iloilo");
		UserForm userForm = new UserForm();
		userForm.setUsername("username");
		userForm.setAddress("Iloilo");
//		userForm.setFullname("John Doe");
		userForm.setPassword("password");
		userForm.setPhoneNumber("09474444834");
//		userForm.setCompany(company);
		User saved = userService.save(userForm);
		Assert.notNull(saved,"User must not be null");
	}
	
	@Test
	@Ignore
	public void updateUserTest(){
		UserForm userForm = new UserForm();
		userForm.setAddress("America");
//		userForm.setFullname("Jane Doe");
		User updated = userService.update(userForm, 4L); 
//		Assert.isTrue(updated.getFullname().equals("Jane Doe"), "Fullname must be equal to 'Jane Doe'");
	}
	
	
}
