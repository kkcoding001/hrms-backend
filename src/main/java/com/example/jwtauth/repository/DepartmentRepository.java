package com.example.jwtauth.repository;

import com.example.jwtauth.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;


public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByName(String name);
    
    Optional<Department> findById(Long id);
    
}