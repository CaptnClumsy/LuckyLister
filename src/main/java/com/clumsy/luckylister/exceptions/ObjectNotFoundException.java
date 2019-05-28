package com.clumsy.luckylister.exceptions;

@SuppressWarnings("serial")
public class ObjectNotFoundException extends RuntimeException {

	public ObjectNotFoundException(Exception e) {
		super(e.getMessage());
	}
	
	public ObjectNotFoundException(String message) {
		super(message);
	}
}

