package com.example.jwtauth.security;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
	
	private static final String SECRET =
	        "mysecretkeymysecretkeymysecretkey12"; // JWT HS256 requires strong key length.
	
	
	// Converts string secret into JWT signing key.
	private Key getSignKey() {
	    return Keys.hmacShaKeyFor(SECRET.getBytes());
	} 
	
	public String generateToken(String username) {

	    return Jwts.builder() 
	            .setSubject(username) // Stores username inside token
	            .setIssuedAt(new Date()) // When token created
	            .setExpiration( 
	                    new Date(System.currentTimeMillis() + 1000 * 60 * 60) // Token expiry ( 1 H )
	            )
	            	// Signs token using ( secret key + algorithm )
	            .signWith(getSignKey(), SignatureAlgorithm.HS256)
	            .compact(); // Final JWT string generated
	}
	
	
	// Extract Username Method
	
	public String extractUsername(String token) {

	    return Jwts.parserBuilder() // read JWT token
	    		// Uses your secret key - token signature must be verified before reading
	            .setSigningKey(getSignKey()) 
	            .build()
	            .parseClaimsJws(token) // Validates signature and Reads payload
	            .getBody() // gets payload
	            .getSubject(); // read username from token
	}
	
	
	// check token expiry
	
	public boolean isTokenExpired(String token) {

	    Date expiration = Jwts.parserBuilder() // // read JWT token
	    		// Uses your secret key - token signature must be verified before reading
	            .setSigningKey(getSignKey())
	            .build()
	            .parseClaimsJws(token) // Validates signature and Reads payload
	            .getBody() // gets payload
	            .getExpiration(); // get expiry time from stored token

	    return expiration.before(new Date()); // check expiration BEFORE current time
	}
	
	
	// validate token
	
	public boolean validateToken(String token, String username) {

	    String extractedUsername = extractUsername(token); // get username from token

	    return extractedUsername.equals(username) // compare username
	            && !isTokenExpired(token); // compare expiry time
	}
	
	
	
	
	
	
	
	
}
