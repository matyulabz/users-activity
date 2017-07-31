package com.users.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.users.entities.Company;
import com.users.entities.Project;
import com.users.entities.Role;
import com.users.entities.User;
import com.users.form.UserForm;
import com.users.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptEncoder;	

	@Override
	public User save(UserForm userForm) {
		if (userForm.getPassword() != null) {
			userForm.setEncryptedPassword(bCryptEncoder.encode(userForm.getPassword()));
		}
		User user = new User(userForm.getUsername(),userForm.getEncryptedPassword(), userForm.getFirstName(), userForm.getLastName(),
				userForm.getAddress(), userForm.getPhoneNumber(), userForm.getCompany());
        Set<Role> roles = userForm.getRole();
        user.setRoles(roles);
        user.getLocation().setLatitude(userForm.getLocation().getLatitude());
        user.getLocation().setLongitude(userForm.getLocation().getLongitude());
		User savedUser = userRepository.save(user);
		return savedUser;
	}
	
	@Override
	public User loadUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> findAll() {
		List<User> users = userRepository.findAll();
		return users;
	}

	@Override
	public User findById(long id) {
		User user = userRepository.findOne(id);
		if (user == null) {
			return null;
		}
		return user;
	}

	@Override
	public User update(UserForm userForm, long id) {
		User user = userRepository.findOne(id);
		if (user == null) {
			return null;
		}
		user.setFirstName(userForm.getFirstName());
		user.setLastName(userForm.getLastName());
		user.setAddress(userForm.getAddress());
		user.setUsername(userForm.getUsername());
		user.setPassword(userForm.getPassword());
		user.setPhoneNumber(userForm.getPhoneNumber());
		user.setCompany(userForm.getCompany());
		user.setTasks(userForm.getTasks());
		User newUser = userRepository.save(user);
		return newUser;
	}

	@Override
	public void deleteUser(long id) {
		userRepository.delete(id);
	}

	@Override
	public List<User> findByCompany(Company company) {
		List<User> users = userRepository.findByCompany(company);
		return users;
	}

	@Override
	public List<User> findInProject(List<User> users, Project project) {
		return users.stream()
			.filter(u -> project.getUsers().contains(u))
			.collect(Collectors.toList());
	}

	@Override
	public List<User> findNotInProject(List<User> users, Project project) {
		List<User> ulist = users.stream()
			.filter(u -> !project.getUsers().contains(u))
			.collect(Collectors.toList());
		return ulist;
	}

	@Override
	public User updatePicture(byte[] photo, long userId) {
		User user = userRepository.findOne(userId);
		if (user == null) {
			return null;
		}
		user.setProfilePicture(photo);
		User updatedUser = userRepository.save(user);
		return updatedUser;
	}
}
