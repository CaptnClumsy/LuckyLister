package com.clumsy.luckylister.exceptions;

@SuppressWarnings("serial")
public class UserServiceException extends RuntimeException {

	public UserServiceException(Exception e) {
		super(e.getMessage());
	}
	
	public UserServiceException(String message) {
		super(message);
	}
}
