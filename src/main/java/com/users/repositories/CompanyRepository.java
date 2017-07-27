package com.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.users.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
