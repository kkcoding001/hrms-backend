package com.example.jwtauth.service;

import java.util.Optional;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.example.jwtauth.dto.DepartmentRequest;
import com.example.jwtauth.entity.Department;
import com.example.jwtauth.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	private final DepartmentRepository departmentRepository;
	
	public DepartmentService(DepartmentRepository departmentRepository) {

        this.departmentRepository = departmentRepository;
    }
	
	// Create Department
	
	public Department createDepartment(DepartmentRequest request) {
		
		if(departmentRepository
				.findByName(request.getName())
				.isPresent()) {
			
			throw new RuntimeErrorException(null, "Department already exists");
		}
		
		Department department = new Department();
		
		department.setName(request.getName());
		department.setDescription(request.getDescription());
		department.setActive(true);
		
		return departmentRepository.save(department); 
	}

	
	// Get Department By Id
	
	public Department getDepartmentById(Long id) {
		
		Optional<Department> department = departmentRepository
								.findById(id);
		
		return department
				.orElseThrow( () -> new RuntimeException("Department not exists"));
	}
	
	
	
	// Get All Departments
	
	public List<Department> getDepartments() {
		
		return departmentRepository.findAll();
	}
	
	
	
}
