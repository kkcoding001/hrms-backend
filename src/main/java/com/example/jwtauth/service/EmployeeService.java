package com.example.jwtauth.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.jwtauth.dto.EmployeeRequest;
import com.example.jwtauth.entity.Employee;
import com.example.jwtauth.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	
	public EmployeeService(EmployeeRepository employeeRepository) {
	    this.employeeRepository = employeeRepository;
	}

	public Employee createEmployee(EmployeeRequest request) {
		
		if(employeeRepository
				.findByEmail(request.getEmail())
				.isPresent()) {
			
			throw new RuntimeException("Employee already exists");
		}
		
		Employee employee = new Employee();
		
		employee.setFirstName(request.getFirstName());
		employee.setLastName(request.getLastName());
		employee.setEmail(request.getEmail());
		employee.setPhone(request.getPhone());
		employee.setSalary(request.getSalary());
		
		return employeeRepository.save(employee);
		
		
	}
}
