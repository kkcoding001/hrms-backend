package com.example.jwtauth.service;

import java.util.Optional;  
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.jwtauth.dto.DepartmentRequest;
import com.example.jwtauth.entity.Department;
import com.example.jwtauth.exception.DepartmentAlreadyExistsException;
import com.example.jwtauth.exception.DepartmentNotFoundException;
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
			
			throw new DepartmentAlreadyExistsException("Department already exists");
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
				.orElseThrow( () -> new DepartmentNotFoundException("Department not found"));
	}
	
	
	
	// Get All Departments
	
	public List<Department> getDepartments() {
		
		return departmentRepository.findAll();
	}
	
	
	
	// Update department
	
	public Department updateDepartment(Long id, DepartmentRequest request) {
		
		Department department = getDepartmentById(id);
		
		Optional<Department> existingDepartment = departmentRepository.findByName(request.getName());
		
		// if throw immediately exits
		
		if(existingDepartment.isPresent() &&  // If department exists with that name
				!existingDepartment.get().getId()  // Id not same with existing department
				.equals(department.getId())) {
			
			throw new DepartmentAlreadyExistsException("Department name already exists");
		}
		
		department.setName(request.getName());
		department.setDescription(request.getDescription());
		
		
		return departmentRepository.save(department);
		
	}
	
	
	// Disable department
	
	public Department disableDepartment(Long id) {
		
		Department department = getDepartmentById(id);
		
		department.setActive(false);
		
		return departmentRepository.save(department);
		 
	}
	
	
	// Enable department
	
		public Department enableDepartment(Long id) {
			
			Department department = getDepartmentById(id);
			
			department.setActive(true);
			
			return departmentRepository.save(department);
			 
		}
	
	
}
