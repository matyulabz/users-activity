package com.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.users.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

}
