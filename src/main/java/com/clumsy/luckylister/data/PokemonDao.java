package com.clumsy.luckylister.data;

import com.clumsy.luckylister.entities.PokemonEntity;

import lombok.Data;

@Data
public class PokemonDao {

	private Long id;
	private Long dexid;
	private String name;
	private boolean done;
	private boolean available;
	private boolean shiny;
	private Long costume;
	private Long region;

	public PokemonDao() {
	}
	
	public PokemonDao(final Long id, final Long dexid, final String name, boolean available,
		boolean shiny, Long costume, Long region) {
		this.id=id;
		this.dexid=dexid;
		this.name=name;
		this.done=true;
		this.available=available;
		this.shiny=shiny;
		this.costume=costume;
		this.region=region;
	}

	public static PokemonDao fromEntity(PokemonEntity pokemon) {
		return new PokemonDao(pokemon.getId(), pokemon.getDexid(),
		    pokemon.getName(), pokemon.getAvailable(),
		    pokemon.getShiny(), pokemon.getCostume(), pokemon.getRegion());
	}

}
