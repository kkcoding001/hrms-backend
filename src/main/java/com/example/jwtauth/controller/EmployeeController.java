package com.example.jwtauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	
	@GetMapping("/employee/dashboard")
    public String employeeDashboard() {
        return "Employee Dashboard";
    }
}
