package com.example.jwtauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwtauth.dto.AuthRequest;
import com.example.jwtauth.entity.User;
import com.example.jwtauth.security.JwtService;
import com.example.jwtauth.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	// Controller needs token service.
	@Autowired 
	private JwtService jwtService;

	
	@PostMapping("/register")
	private User register(@RequestBody User user) {
		return userService.register(user);
	}
	
	
	@PostMapping("/login")
	public String login(@RequestBody AuthRequest request) {

	    Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(
	                    request.getUsername(),
	                    request.getPassword() 
	            )
	    );

	    if (authentication.isAuthenticated()) {
	        //return "Login successful";
	    	return jwtService.generateToken(request.getUsername());
	    } 
	    else {
	        return "Invalid credentials";
	    }
	} 
	
	
	@GetMapping("/hello")
	public String hello() {
	    return "Hello User";
	}
	
}
