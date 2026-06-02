package com.example.jwtauth.exception;

public class DepartmentAlreadyExistsException extends RuntimeException{

	public DepartmentAlreadyExistsException(String message) {
		super(message);
	}
}
