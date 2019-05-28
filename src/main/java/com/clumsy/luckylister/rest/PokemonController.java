package com.clumsy.luckylister.rest;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clumsy.luckylister.data.PokemonDao;
import com.clumsy.luckylister.entities.UserEntity;
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
		try {
			UserEntity user = userService.getCurrentUser(principal);
			List<PokemonDao> pokemon = pokemonService.listPokemon(user);
			return pokemon;
		} catch (UserNotFoundException e) {
			throw new ObjectNotFoundException("Current user not found");
		}
    }
}
