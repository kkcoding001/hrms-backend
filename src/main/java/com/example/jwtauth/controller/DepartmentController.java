package com.example.jwtauth.controller; 

import java.util.List; 

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwtauth.dto.DepartmentRequest;
import com.example.jwtauth.entity.Department;
import com.example.jwtauth.repository.DepartmentRepository;
import com.example.jwtauth.service.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentRepository departmentRepository;

	private final DepartmentService departmentService;
	
	public DepartmentController(DepartmentService departmentService, DepartmentRepository departmentRepository) {
		this.departmentService = departmentService;
		this.departmentRepository = departmentRepository;
	}
	
	
	// Create Department
	
	@PostMapping
	public ResponseEntity<Department> createDepartment(
	        @RequestBody DepartmentRequest request) {

	    Department department = departmentService.createDepartment(request);

	    return ResponseEntity
	            .status(HttpStatus.CREATED)
	            .body(department);
	}
	
	
	// Get Department By Id
	
	@GetMapping("/{id}")
	public ResponseEntity<Department> getDepartmentById(
			@PathVariable Long id) {
		
		Department department = departmentService.getDepartmentById(id);
		
		return ResponseEntity
				.status(HttpStatus.OK)
	            .body(department);
	}
	
	
	// Get All Departments
	@GetMapping
	public ResponseEntity<List<Department>> getDepartments() {
		
		List<Department> department =  departmentService.getDepartments();
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(department);
		
	}
	
	 
	// Update department
	@PutMapping("/{id}")
	public ResponseEntity<Department> updateDepartmentById(
			@PathVariable Long id,
			@RequestBody DepartmentRequest request) {
		
		Department department = departmentService.updateDepartment(id, request);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(department);
		
	}
	
	
	// Disable department
	
	@PatchMapping("/{id}/disable")
	public ResponseEntity<Department> disableDepartment(
	        @PathVariable Long id) {

		Department department = departmentService.disableDepartment(id);

	    return ResponseEntity
				.status(HttpStatus.OK)
				.body(department);
	}
	
	
	// Enable department
	
	@PatchMapping("/{id}/enable")
	public ResponseEntity<Department> enableDepartment(
	        @PathVariable Long id) {

		Department department = departmentService.enableDepartment(id);

	    return ResponseEntity
				.status(HttpStatus.OK)
				.body(department);
	}
	
	 
	
}
