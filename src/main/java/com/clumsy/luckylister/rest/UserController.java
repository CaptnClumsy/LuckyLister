package com.clumsy.luckylister.rest;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clumsy.luckylister.data.LeaderDao;
import com.clumsy.luckylister.data.SelectListDao;
import com.clumsy.luckylister.data.TotalDao;
import com.clumsy.luckylister.data.UserDao;
import com.clumsy.luckylister.entities.UserEntity;
import com.clumsy.luckylister.exceptions.NotLoggedInException;
import com.clumsy.luckylister.exceptions.ObjectNotFoundException;
import com.clumsy.luckylister.exceptions.UserAlreadyRegisteredException;
import com.clumsy.luckylister.exceptions.UserNotFoundException;
import com.clumsy.luckylister.exceptions.UserServiceException;
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
		} catch (UserAlreadyRegisteredException e) {
			throw new UserServiceException(e.getMessage());
		}
    }
	
	@RequestMapping("/user/all")
	public List<SelectListDao> users(Principal principal) {
		if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
		try {
			List<UserEntity> users = userService.getAllUsers();
			List<SelectListDao> userDaos = new ArrayList<>(users.size());
			for (UserEntity user : users) {
				userDaos.add(SelectListDao.fromUserEntity(user));
			}
			return userDaos;
		} catch (UserNotFoundException e) {
			throw new ObjectNotFoundException("No users found");
		}
    }
	
	@RequestMapping("/user/stats")
	public TotalDao stats(Principal principal) {
		if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
		try {
			UserEntity user = userService.getCurrentUser(principal);
			TotalDao stats = userService.getStats(user);
			return stats;
		} catch (UserNotFoundException e) {
			throw new ObjectNotFoundException("Current user not found");
		} catch (UserAlreadyRegisteredException e) {
			throw new UserServiceException(e.getMessage());
		}
    }

	@RequestMapping(value = "/user/leaderboard")
    public List<LeaderDao> getLeaderboard(Principal principal) {
    	if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
    	try {
    	    UserEntity user = userService.getCurrentUser(principal);
		    return userService.getLeaderboard(user);
    	} catch (UserNotFoundException e) {
    		throw new ObjectNotFoundException("Current user not found");
    	} catch (UserAlreadyRegisteredException e) {
    		throw new UserServiceException(e.getMessage());
		}
	}
	
	@RequestMapping("/user/pokemon/{id}")
	public List<SelectListDao> users(@PathVariable("id") Long pokemonId, Principal principal) {
		if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
		List<UserEntity> users = userService.getAllUsersWithPokemon(pokemonId);
		List<SelectListDao> userDaos = new ArrayList<>(users.size());
		for (UserEntity user : users) {
			userDaos.add(SelectListDao.fromUserEntity(user));
		}
		return userDaos;
    }

}
