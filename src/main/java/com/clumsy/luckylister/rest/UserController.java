package com.clumsy.luckylister.rest;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clumsy.luckylister.data.UserDao;
import com.clumsy.luckylister.entities.UserEntity;
import com.clumsy.luckylister.exceptions.ObjectNotFoundException;
import com.clumsy.luckylister.exceptions.UserNotFoundException;
import com.clumsy.luckylister.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/user")
	public UserDao user(Principal principal) {
		try {
			UserEntity user = userService.getCurrentUser(principal);
			return UserDao.fromEntity(user);
		} catch (UserNotFoundException e) {
			throw new ObjectNotFoundException("Current user not found");
		}
    }
}
