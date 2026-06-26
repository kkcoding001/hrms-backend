package com.example.jwtauth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwtauth.dto.EmployeeRequest;
import com.example.jwtauth.entity.Employee;
import com.example.jwtauth.service.EmployeeService;


@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
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
}
