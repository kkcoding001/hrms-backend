package com.example.jwtauth.service;

import java.util.List; 
import java.util.Optional; 

import org.springframework.stereotype.Service;

import com.example.jwtauth.dto.EmployeeRequest;
import com.example.jwtauth.entity.Department;
import com.example.jwtauth.entity.Employee;
import com.example.jwtauth.repository.DepartmentRepository;
import com.example.jwtauth.repository.EmployeeRepository;
import com.example.jwtauth.exception.EmployeeNotFoundException;
import com.example.jwtauth.exception.EmployeeAlreadyExistsException;

@Service
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	
	private final DepartmentRepository departmentRepository;
	
	public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
	    this.employeeRepository = employeeRepository;
	    this.departmentRepository = departmentRepository;
	}

	
	// Create Employee
	public Employee createEmployee(EmployeeRequest request) {
		
		if(employeeRepository
				.findByEmail(request.getEmail())
				.isPresent()) {
			
			throw new EmployeeAlreadyExistsException("Employee already exists");
		}
		
		Department department = departmentRepository
		        .findById(request.getDepartmentId())
		        .orElseThrow(() -> new RuntimeException("Department not found"));
		
		Employee employee = new Employee();
		
		employee.setFirstName(request.getFirstName());
		employee.setLastName(request.getLastName());
		employee.setEmail(request.getEmail());
		employee.setPhone(request.getPhone());
		employee.setSalary(request.getSalary());
		employee.setDepartment(department);
		
		return employeeRepository.save(employee);
		
	}
	
	
	// Get Employee By Id
	public Employee getEmployeeById(Long id) {
		
		Optional<Employee> employee = employeeRepository.findById(id);
		
		return employee
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with this id"));
	}
	
	
	// Get all employee
	public List<Employee> getAllEmployee() {
		
		return employeeRepository.findAll();
	}
	
	
	// Update employee
	public Employee updateEmployeeById(Long id, EmployeeRequest request) {
		
		Employee employee = getEmployeeById(id);
		
		Optional<Employee> existingEmployee = employeeRepository.findByEmail(request.getEmail());
		
		if(existingEmployee.isPresent() // If employee exists with that name
				&&
			!existingEmployee.get().getId() // Id not match with existing employee
			.equals(employee.getId()))
		{
			throw new EmployeeAlreadyExistsException("Email belongs to another employee");
		}
		
		Department department = departmentRepository
		        .findById(request.getDepartmentId())
		        .orElseThrow(() -> new RuntimeException("Department not found"));
		
		employee.setFirstName(request.getFirstName());
		employee.setLastName(request.getLastName());
		employee.setEmail(request.getEmail());
		employee.setPhone(request.getPhone());
		employee.setSalary(request.getSalary());
		employee.setDepartment(department);
		
		return employeeRepository.save(employee);
		
		
	}
	
	
	
	// Disable Employee
	public Employee disableEmployee(Long id) {
		
		Employee employee = getEmployeeById(id);
		
		employee.setActive(false);
		
		return employeeRepository.save(employee);
	}
	
	// Enable Employee
	public Employee enableEmployee(Long id) {
			
		Employee employee = getEmployeeById(id);
		
		employee.setActive(true);
		
		return employeeRepository.save(employee);
	}
	
}
