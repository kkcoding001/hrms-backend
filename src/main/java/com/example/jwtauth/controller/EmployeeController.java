package com.example.jwtauth.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwtauth.dto.EmployeeRequest;
import com.example.jwtauth.entity.Employee;
import com.example.jwtauth.service.EmployeeService;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final AuthController authController;
	
	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService, AuthController authController) {
		this.employeeService = employeeService;
		this.authController = authController;
	}
	
	@PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeRequest request) {
		
		Employee employee = employeeService.createEmployee(request);
		
        return ResponseEntity
        		.status(HttpStatus.CREATED)
        		.body(employee);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		
		Employee employee = employeeService.getEmployeeById(id);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(employee);
	}
	
	@GetMapping 
	public ResponseEntity<List<Employee>> getAllEmployee() {
		
		List<Employee> employee = employeeService.getAllEmployee();
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(employee); 
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployeeById(
			@PathVariable Long id,
			@RequestBody EmployeeRequest request) {
		
		Employee employee = employeeService.updateEmployeeById(id, request);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(employee);
		
		
	}
	
	
	@PatchMapping("/{id}/disable")
	public ResponseEntity<Employee> disableEmployee(@PathVariable Long id) {
		
		Employee employee = employeeService.disableEmployee(id);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(employee);
		
	}
	
	@PatchMapping("/{id}/enable")
	public ResponseEntity<Employee> enableEmployee(@PathVariable Long id) {
		
		Employee employee = employeeService.enableEmployee(id);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(employee);
		
	}
	
}
