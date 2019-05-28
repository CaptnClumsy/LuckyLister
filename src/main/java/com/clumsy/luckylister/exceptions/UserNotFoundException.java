package com.clumsy.luckylister.exceptions;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception {
	public UserNotFoundException(final String message) {
        super(message);
    }
}
