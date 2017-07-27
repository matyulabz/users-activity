package com.users.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.users.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

	List<Project> findByDoneFalse();
}
