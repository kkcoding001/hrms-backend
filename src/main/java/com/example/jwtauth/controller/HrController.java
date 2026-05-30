package com.example.jwtauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HrController {
	@GetMapping("/hr/dashboard")
    public String hrDashboard() {
        return "HR Dashboard";
    }
}
