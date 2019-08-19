package com.clumsy.luckylister.rest;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.clumsy.luckylister.data.FriendDao;
import com.clumsy.luckylister.data.LeaderDao;
import com.clumsy.luckylister.data.PokemonDao;
import com.clumsy.luckylister.data.PrefsDao;
import com.clumsy.luckylister.data.TotalDao;
import com.clumsy.luckylister.data.UpdateFriendDao;
import com.clumsy.luckylister.data.UserDao;
import com.clumsy.luckylister.entities.UserEntity;
import com.clumsy.luckylister.exceptions.NotLoggedInException;
import com.clumsy.luckylister.exceptions.ObjectNotFoundException;
import com.clumsy.luckylister.exceptions.UserAlreadyRegisteredException;
import com.clumsy.luckylister.exceptions.UserNotFoundException;
import com.clumsy.luckylister.exceptions.UserServiceException;
import com.clumsy.luckylister.services.PokemonService;
import com.clumsy.luckylister.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PokemonService pokemonService;

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
	
	@RequestMapping(value = "/user/prefs", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public UserDao updateUser(@RequestBody PrefsDao updateData, Principal principal) {
    	if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
		try {
			final UserEntity user = userService.getCurrentUser(principal);
            final UserDao dao = userService.updateUser(user, updateData.isCostumes());
            return dao;
		} catch (UserNotFoundException e) {
			throw new ObjectNotFoundException("Current user not found");
		} catch (UserAlreadyRegisteredException e) {
			throw new UserServiceException(e.getMessage());
		}
    }
	
	@RequestMapping("/user/all")
	public List<UserDao> users(Principal principal) {
		if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
		try {
			UserEntity user = userService.getCurrentUser(principal);
			List<UserDao> users = userService.getAllUsers(user);
			return users;
		} catch (UserNotFoundException e) {
			throw new ObjectNotFoundException("No users found");
		} catch (UserAlreadyRegisteredException e) {
			throw new UserServiceException(e.getMessage());
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
	public List<UserDao> users(@PathVariable("id") Long pokemonId, Principal principal) {
		if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
		try {
			UserEntity user = userService.getCurrentUser(principal);
			List<UserDao> users = userService.getAllUsersWithPokemon(user, pokemonId);
			return users;
		} catch (UserNotFoundException e) {
    		throw new ObjectNotFoundException("Current user not found");
    	} catch (UserAlreadyRegisteredException e) {
    		throw new UserServiceException(e.getMessage());
		}
    }

	@RequestMapping("/user/friends")
	public List<FriendDao> friends(Principal principal) {
		if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
		try {
			UserEntity user = userService.getCurrentUser(principal);
			List<FriendDao> friendDaos = userService.getAllUsersAndFriends(user);
			return friendDaos;
		} catch (UserNotFoundException e) {
			throw new ObjectNotFoundException("No users found");
		} catch (UserAlreadyRegisteredException e) {
			throw new UserServiceException(e.getMessage());
		}
    }
	
	@RequestMapping(value = "/user/friend/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public FriendDao updateFriend(@RequestBody UpdateFriendDao updateData, @PathVariable("id") Long friendId, Principal principal) {
    	if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
		try {
			final UserEntity user = userService.getCurrentUser(principal);
            final FriendDao dao = userService.updateFriend(user, friendId, updateData.isFriends());
            return dao;
		} catch (UserNotFoundException e) {
			throw new ObjectNotFoundException("Current user not found");
		} catch (UserAlreadyRegisteredException e) {
			throw new UserServiceException(e.getMessage());
		}
    }
	
	@RequestMapping("/shiny/user/stats")
	public TotalDao luckyStats(Principal principal) {
		if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
		try {
			UserEntity user = userService.getCurrentUser(principal);
			TotalDao stats = userService.getShinyStats(user);
			return stats;
		} catch (UserNotFoundException e) {
			throw new ObjectNotFoundException("Current user not found");
		} catch (UserAlreadyRegisteredException e) {
			throw new UserServiceException(e.getMessage());
		}
    }
	
	@RequestMapping(value = "/shiny/user/leaderboard")
    public List<LeaderDao> getShinyLeaderboard(Principal principal) {
    	if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
    	try {
    	    UserEntity user = userService.getCurrentUser(principal);
		    return userService.getShinyLeaderboard(user);
    	} catch (UserNotFoundException e) {
    		throw new ObjectNotFoundException("Current user not found");
    	} catch (UserAlreadyRegisteredException e) {
    		throw new UserServiceException(e.getMessage());
		}
	}
	
	@RequestMapping("/shiny/user/pokemon/{id}")
	public List<UserDao> usersShiny(@PathVariable("id") Long pokemonId, Principal principal) {
		if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
		try {
			UserEntity user = userService.getCurrentUser(principal);
			PokemonDao p = pokemonService.getPokemon(pokemonId);
			List<UserDao> users = userService.getAllUsersWithShinyPokemon(user, p.getDexid());
			return users;
		} catch (UserNotFoundException e) {
    		throw new ObjectNotFoundException("Current user not found");
    	} catch (UserAlreadyRegisteredException e) {
    		throw new UserServiceException(e.getMessage());
		}
    }
	
	@RequestMapping("/hundo/user/pokemon/{id}")
	public List<UserDao> usersHundo(@PathVariable("id") Long pokemonId, Principal principal) {
		if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
		try {
			UserEntity user = userService.getCurrentUser(principal);
			PokemonDao p = pokemonService.getPokemon(pokemonId);
			List<UserDao> users = userService.getAllUsersWithHundoPokemon(user, p.getDexid());
			return users;
		} catch (UserNotFoundException e) {
    		throw new ObjectNotFoundException("Current user not found");
    	} catch (UserAlreadyRegisteredException e) {
    		throw new UserServiceException(e.getMessage());
		}
    }
	
	@RequestMapping("/hundo/user/stats")
	public TotalDao hundoStats(Principal principal) {
		if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
		try {
			UserEntity user = userService.getCurrentUser(principal);
			TotalDao stats = userService.getHundoStats(user);
			return stats;
		} catch (UserNotFoundException e) {
			throw new ObjectNotFoundException("Current user not found");
		} catch (UserAlreadyRegisteredException e) {
			throw new UserServiceException(e.getMessage());
		}
    }
	
	@RequestMapping(value = "/hundo/user/leaderboard")
    public List<LeaderDao> getHundoLeaderboard(Principal principal) {
    	if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
    	try {
    	    UserEntity user = userService.getCurrentUser(principal);
		    return userService.getHundoLeaderboard(user);
    	} catch (UserNotFoundException e) {
    		throw new ObjectNotFoundException("Current user not found");
    	} catch (UserAlreadyRegisteredException e) {
    		throw new UserServiceException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/hundo/user/countboard")
    public List<LeaderDao> getCountboard(Principal principal) {
    	if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
    	try {
    	    UserEntity user = userService.getCurrentUser(principal);
		    return userService.getHundoCountboard(user);
    	} catch (UserNotFoundException e) {
    		throw new ObjectNotFoundException("Current user not found");
    	} catch (UserAlreadyRegisteredException e) {
    		throw new UserServiceException(e.getMessage());
		}
	}
}
