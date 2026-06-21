package com.example.jwtauth.controller;

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
    public Employee createEmployee(@RequestBody EmployeeRequest request) {
		
        return employeeService.createEmployee(request);
    }
}
