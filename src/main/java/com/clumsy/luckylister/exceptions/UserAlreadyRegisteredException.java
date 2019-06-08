package com.clumsy.luckylister.exceptions;

@SuppressWarnings("serial")
public class UserAlreadyRegisteredException extends Exception {
	public UserAlreadyRegisteredException(final String message) {
        super(message);
    }
}
