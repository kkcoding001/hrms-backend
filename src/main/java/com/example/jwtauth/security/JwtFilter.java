package com.example.jwtauth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtService jwtService; // read & validate token
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService; // load user from DB
	
	
	
	// runs for every request
	
	@Override
	protected void doFilterInternal(
	        HttpServletRequest request,
	        HttpServletResponse response,
	        FilterChain filterChain)
	        throws ServletException, IOException {
		
		System.out.println("JwtFilter running");
		
		// read header
		String authHeader = request.getHeader("Authorization");
		
		// If no token
		
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {

		    filterChain.doFilter(request, response);
		    return;
		}
		
		
		// extract token
		
		String token = authHeader.substring(7); // ( remove ) Bearer has 7 characters
		
		// extract username
		
		String username = jwtService.extractUsername(token);
		
		System.out.println("Username from token: " + username);
		
		
		// If token has username && user not already logged in THEN continue
		// SecurityContextHolder = contains Current request's logged-in user
		
		if (username != null &&
			    SecurityContextHolder.getContext().getAuthentication() == null) {
			
			
			// load user from DB
			
			UserDetails userDetails =
					customUserDetailsService.loadUserByUsername(username);
			
			
			// token belongs to correct user && not expired
			
			if (jwtService.validateToken(token, userDetails.getUsername())) {
				
				
				System.out.println(
					    jwtService.validateToken(token, userDetails.getUsername())
					);
				
				// create authentication object
				
				/***
				 
					Who is user?
					What are roles?
					Is authenticated? 
					
				***/
				UsernamePasswordAuthenticationToken authToken =
				        new UsernamePasswordAuthenticationToken(
				                userDetails,
				                null, // Password not needed already authenticated
				                // stores authorities inside UserDetails object
				                userDetails.getAuthorities() // roles/permissions
				        );
				
				/***
				  
					Extra request info:
					
					IP address
					session info
					request details
					
				 ***/
				authToken.setDetails(
				        new WebAuthenticationDetailsSource().buildDetails(request)
				);
				
				// tells spring request is authenticated
				SecurityContextHolder.getContext().setAuthentication(authToken);
				
				
			}
			
		}
		
		System.out.println(
			    SecurityContextHolder.getContext().getAuthentication().getAuthorities()
			);
		
		filterChain.doFilter(request, response);
	}
	
}
