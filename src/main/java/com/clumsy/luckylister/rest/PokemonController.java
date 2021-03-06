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

import com.clumsy.luckylister.data.PokemonDao;
import com.clumsy.luckylister.data.PokemonSelectListDao;
import com.clumsy.luckylister.data.UpdatePokemonDao;
import com.clumsy.luckylister.entities.UserEntity;
import com.clumsy.luckylister.exceptions.NotLoggedInException;
import com.clumsy.luckylister.exceptions.ObjectNotFoundException;
import com.clumsy.luckylister.exceptions.UserAlreadyRegisteredException;
import com.clumsy.luckylister.exceptions.UserNotFoundException;
import com.clumsy.luckylister.exceptions.UserServiceException;
import com.clumsy.luckylister.services.PokemonService;
import com.clumsy.luckylister.services.UserService;

@RestController
public class PokemonController {

	@Autowired
	private UserService userService;

	@Autowired
	private PokemonService pokemonService;

	@RequestMapping("/pokemon")
	public List<PokemonDao> listPokemon(Principal principal) {
		if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
		try {
			UserEntity user = userService.getCurrentUser(principal);
			List<PokemonDao> pokemon = pokemonService.listPokemon(user);
			return pokemon;
		} catch (UserNotFoundException e) {
			throw new ObjectNotFoundException("Current user not found");
		} catch (UserAlreadyRegisteredException e) {
			throw new UserServiceException(e.getMessage());
		}
    }
	
	@RequestMapping(value = "/pokemon/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public PokemonDao updatePokemon(@RequestBody UpdatePokemonDao updateData, @PathVariable("id") Long pokemonId, Principal principal) {
    	if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
		try {
			final UserEntity user = userService.getCurrentUser(principal);
            final PokemonDao dao = pokemonService.updatePokemon(user, pokemonId, updateData.isSelected());
            return dao;
		} catch (UserNotFoundException e) {
			throw new ObjectNotFoundException("Current user not found");
		} catch (UserAlreadyRegisteredException e) {
			throw new UserServiceException(e.getMessage());
		}
    }
	
	@RequestMapping("/pokemon/user/{id}")
	public List<PokemonDao> listPokemonForUser(@PathVariable("id") Long userId, Principal principal) {
		if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
		try {
			UserEntity user = userService.getUser(userId);
			List<PokemonDao> pokemon = pokemonService.listPokemonForUser(user);
			return pokemon;
		} catch (UserNotFoundException e) {
			throw new ObjectNotFoundException("User not found");
		}
    }

	@RequestMapping("/pokemon/all")
	public List<PokemonSelectListDao> listAllPokemon(Principal principal) {
		if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
		try {
			List<PokemonSelectListDao> pokemon = pokemonService.listAllPokemon();
			return pokemon;
		} catch (ObjectNotFoundException e) {
			throw new ObjectNotFoundException("No Pokemon found");
		}
    }
	
	@RequestMapping("/shiny/pokemon/all")
	public List<PokemonSelectListDao> listAllShinyPokemon(Principal principal) {
		if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
		try {
			List<PokemonSelectListDao> pokemon = pokemonService.listAllShinyPokemon();
			return pokemon;
		} catch (ObjectNotFoundException e) {
			throw new ObjectNotFoundException("No Pokemon found");
		}
    }
	
	@RequestMapping("/hundo/pokemon/all")
	public List<PokemonSelectListDao> listAllHundoPokemon(Principal principal) {
		if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
		try {
			List<PokemonSelectListDao> pokemon = pokemonService.listAllHundoPokemon();
			return pokemon;
		} catch (ObjectNotFoundException e) {
			throw new ObjectNotFoundException("No Pokemon found");
		}
    }
	
	@RequestMapping("/98/pokemon/all")
	public List<PokemonSelectListDao> listAllNinetyEightPokemon(Principal principal) {
		return listAllHundoPokemon(principal);
    }
	
	@RequestMapping("/shadow/pokemon/all")
	public List<PokemonSelectListDao> listAllShadowPokemon(Principal principal) {
		if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
		try {
			List<PokemonSelectListDao> pokemon = pokemonService.listAllShadowPokemon();
			return pokemon;
		} catch (ObjectNotFoundException e) {
			throw new ObjectNotFoundException("No Pokemon found");
		}
    }
	
	@RequestMapping("/shiny/pokemon")
	public List<PokemonDao> listShinyPokemon(Principal principal) {
		if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
		try {
			UserEntity user = userService.getCurrentUser(principal);
			List<PokemonDao> pokemon = pokemonService.listShinyPokemon(user);
			return pokemon;
		} catch (UserNotFoundException e) {
			throw new ObjectNotFoundException("Current user not found");
		} catch (UserAlreadyRegisteredException e) {
			throw new UserServiceException(e.getMessage());
		}
    }
	
	@RequestMapping("/shadow/pokemon")
	public List<PokemonDao> listShadowPokemon(Principal principal) {
		if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
		try {
			UserEntity user = userService.getCurrentUser(principal);
			List<PokemonDao> pokemon = pokemonService.listShadowPokemon(user);
			return pokemon;
		} catch (UserNotFoundException e) {
			throw new ObjectNotFoundException("Current user not found");
		} catch (UserAlreadyRegisteredException e) {
			throw new UserServiceException(e.getMessage());
		}
    }
	
	@RequestMapping(value = "/shiny/pokemon/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public PokemonDao updateShinyPokemon(@RequestBody UpdatePokemonDao updateData, @PathVariable("id") Long pokemonId, Principal principal) {
    	if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
		try {
			final UserEntity user = userService.getCurrentUser(principal);
            final PokemonDao dao = pokemonService.updateShinyPokemon(user, pokemonId, updateData.isSelected());
            return dao;
		} catch (UserNotFoundException e) {
			throw new ObjectNotFoundException("Current user not found");
		} catch (UserAlreadyRegisteredException e) {
			throw new UserServiceException(e.getMessage());
		}
    }
	
	@RequestMapping(value = "/shadow/pokemon/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public PokemonDao updateShadowPokemon(@RequestBody UpdatePokemonDao updateData, @PathVariable("id") Long pokemonId, Principal principal) {
    	if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
		try {
			final UserEntity user = userService.getCurrentUser(principal);
            final PokemonDao dao = pokemonService.updateShadowPokemon(user, pokemonId, updateData.isSelected());
            return dao;
		} catch (UserNotFoundException e) {
			throw new ObjectNotFoundException("Current user not found");
		} catch (UserAlreadyRegisteredException e) {
			throw new UserServiceException(e.getMessage());
		}
    }
	
	@RequestMapping("/shiny/pokemon/user/{id}")
	public List<PokemonDao> listShinyPokemonForUser(@PathVariable("id") Long userId, Principal principal) {
		if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
		try {
			UserEntity user = userService.getUser(userId);
			List<PokemonDao> pokemon = pokemonService.listShinyPokemonForUser(user);
			return pokemon;
		} catch (UserNotFoundException e) {
			throw new ObjectNotFoundException("User not found");
		}
    }
	
	@RequestMapping("/shadow/pokemon/user/{id}")
	public List<PokemonDao> listShadowPokemonForUser(@PathVariable("id") Long userId, Principal principal) {
		if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
		try {
			UserEntity user = userService.getUser(userId);
			List<PokemonDao> pokemon = pokemonService.listShadowPokemonForUser(user);
			return pokemon;
		} catch (UserNotFoundException e) {
			throw new ObjectNotFoundException("User not found");
		}
    }
	
	@RequestMapping("/hundo/pokemon")
	public List<PokemonDao> listHundoPokemon(Principal principal) {
		if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
		try {
			UserEntity user = userService.getCurrentUser(principal);
			List<PokemonDao> pokemon = pokemonService.listHundoPokemon(user);
			return pokemon;
		} catch (UserNotFoundException e) {
			throw new ObjectNotFoundException("Current user not found");
		} catch (UserAlreadyRegisteredException e) {
			throw new UserServiceException(e.getMessage());
		}
    }
	
	@RequestMapping("/98/pokemon")
	public List<PokemonDao> listninetyEightPokemon(Principal principal) {
		if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
		try {
			UserEntity user = userService.getCurrentUser(principal);
			List<PokemonDao> pokemon = pokemonService.listNinetyEightPokemon(user);
			return pokemon;
		} catch (UserNotFoundException e) {
			throw new ObjectNotFoundException("Current user not found");
		} catch (UserAlreadyRegisteredException e) {
			throw new UserServiceException(e.getMessage());
		}
    }

	@RequestMapping(value = "/hundo/pokemon/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public PokemonDao updateHundoPokemon(@RequestBody UpdatePokemonDao updateData, @PathVariable("id") Long pokemonId, Principal principal) {
    	if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
		try {
			final UserEntity user = userService.getCurrentUser(principal);
            final PokemonDao dao = pokemonService.updateHundoPokemon(user, pokemonId, updateData.isSelected(), updateData.getTotal());
            return dao;
		} catch (UserNotFoundException e) {
			throw new ObjectNotFoundException("Current user not found");
		} catch (UserAlreadyRegisteredException e) {
			throw new UserServiceException(e.getMessage());
		}
    }

	@RequestMapping(value = "/98/pokemon/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public PokemonDao updateNinetyEightPokemon(@RequestBody UpdatePokemonDao updateData, @PathVariable("id") Long pokemonId, Principal principal) {
    	if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
		try {
			final UserEntity user = userService.getCurrentUser(principal);
            final PokemonDao dao = pokemonService.updateNinetyEightPokemon(user, pokemonId, updateData.isSelected(), updateData.getTotal());
            return dao;
		} catch (UserNotFoundException e) {
			throw new ObjectNotFoundException("Current user not found");
		} catch (UserAlreadyRegisteredException e) {
			throw new UserServiceException(e.getMessage());
		}
    }

	@RequestMapping("/hundo/pokemon/user/{id}")
	public List<PokemonDao> listHundoPokemonForUser(@PathVariable("id") Long userId, Principal principal) {
		if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
		try {
			UserEntity user = userService.getUser(userId);
			List<PokemonDao> pokemon = pokemonService.listHundoPokemon(user);
			return pokemon;
		} catch (UserNotFoundException e) {
			throw new ObjectNotFoundException("User not found");
		}
    }
	
	@RequestMapping("/98/pokemon/user/{id}")
	public List<PokemonDao> listNinetyEightPokemonForUser(@PathVariable("id") Long userId, Principal principal) {
		if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
    		throw new NotLoggedInException();
    	}
		try {
			UserEntity user = userService.getUser(userId);
			List<PokemonDao> pokemon = pokemonService.listNinetyEightPokemon(user);
			return pokemon;
		} catch (UserNotFoundException e) {
			throw new ObjectNotFoundException("User not found");
		}
    }

}
