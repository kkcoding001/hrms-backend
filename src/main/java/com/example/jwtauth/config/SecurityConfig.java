package com.example.jwtauth.config;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.example.jwtauth.security.JwtAuthEntryPoint;
import com.example.jwtauth.security.JwtFilter;

@Configuration
public class SecurityConfig {
	
	// needs access to filter
	@Autowired
	private JwtFilter jwtFilter;
	
	@Autowired
	private JwtAuthEntryPoint jwtAuthEntryPoint;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable()) // disable CSRF for testing
            .sessionManagement(session ->
	            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        )
            .exceptionHandling(ex ->
	            ex.authenticationEntryPoint(jwtAuthEntryPoint)
	        )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll() // public
                .anyRequest().authenticated() // else secured
            );
        
        
        // Before checks authentication run filter
        http.addFilterBefore(
                jwtFilter,
                UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	    return config.getAuthenticationManager();
	}
	
}
