package com.example.jwtauth.exception;

import org.springframework.http.HttpStatus;  
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.jwtauth.dto.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(DepartmentNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleDepartmentNotFound(DepartmentNotFoundException ex) {
		
		 return ResponseEntity
		            .status(HttpStatus.NOT_FOUND) // 404
		            .body(new ErrorResponse(
		            		ex.getMessage()
		            		)
		            );	 
	}
	
	@ExceptionHandler(DepartmentAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleDepartmentAlreadyExists(DepartmentAlreadyExistsException ex) {
		
		 return ResponseEntity
		            .status(HttpStatus.CONFLICT) // 409
		            .body(new ErrorResponse(
		            		ex.getMessage()
		            		)
		            );  
	} 
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
		
		return ResponseEntity
	            .status(HttpStatus.NOT_FOUND) // 404
	            .body(new ErrorResponse(
	            		ex.getMessage()
	            		)
	            );
		
	}
	
	
	@ExceptionHandler(EmployeeAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleEmployeeNotFoundException(EmployeeAlreadyExistsException ex) {
		
		return ResponseEntity
	            .status(HttpStatus.CONFLICT) // 409
	            .body(new ErrorResponse(
	            		ex.getMessage()
	            		)
	            );
		
	}
	
} 
