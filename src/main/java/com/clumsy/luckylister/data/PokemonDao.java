package com.clumsy.luckylister.data;

import com.clumsy.luckylister.entities.PokemonEntity;

import lombok.Data;

@Data
public class PokemonDao {

	private Long id;
	private Long dexid;
	private String name;
	private boolean done;

	public PokemonDao() {
	}
	
	public PokemonDao(final Long id, final Long dexid, final String name) {
		this.id=id;
		this.dexid=dexid;
		this.name=name;
		this.done=true;
	}

	public static PokemonDao fromEntity(PokemonEntity pokemon) {
		return new PokemonDao(pokemon.getId(), pokemon.getDexid(), pokemon.getName());
	}

}
