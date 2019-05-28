package com.clumsy.luckylister.data;

import com.clumsy.luckylister.entities.PokemonEntity;

import lombok.Data;

@Data
public class PokemonDao {

	private Long id;
	private String name;
	private String url;
	private boolean done;

	public PokemonDao() {
	}
	
	public PokemonDao(final Long id, final String name) {
		this.id=id;
		this.name=name;
		this.done=true;
	}

	public static PokemonDao fromEntity(PokemonEntity pokemon) {
		return new PokemonDao(pokemon.getId(), pokemon.getName());
	}

}
