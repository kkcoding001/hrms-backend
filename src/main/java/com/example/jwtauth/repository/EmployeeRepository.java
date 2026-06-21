package com.example.jwtauth.repository;

import java.util.Optional;
import com.example.jwtauth.entity.Employee;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	Optional<Employee> findByEmail(String email);
}
