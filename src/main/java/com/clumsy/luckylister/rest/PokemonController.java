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
import com.clumsy.luckylister.data.UpdatePokemonDao;
import com.clumsy.luckylister.entities.UserEntity;
import com.clumsy.luckylister.exceptions.NotLoggedInException;
import com.clumsy.luckylister.exceptions.ObjectNotFoundException;
import com.clumsy.luckylister.exceptions.UserNotFoundException;
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
}
