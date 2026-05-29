package com.example.jwtauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.jwtauth.entity.User;
import com.example.jwtauth.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	 private PasswordEncoder passwordEncoder;
	
	
	public User register(User user) {
		
		// encrypt password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		// default role
		user.setRole("USER");
		
		return userRepository.save(user);
	}

}
