package com.users.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.users.entities.Company;
import com.users.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	List<User> findByCompany(Company companyId);
	
	User findByUsername(String username);
	
}
