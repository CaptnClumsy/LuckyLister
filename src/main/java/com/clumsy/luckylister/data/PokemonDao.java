package com.clumsy.luckylister.data;

import com.clumsy.luckylister.entities.PokemonEntity;

import lombok.Data;

@Data
public class PokemonDao {

	private Long id;
	private Long dexid;
	private String name;
	private boolean done;
	private boolean lucky;
	private boolean shiny;
	private boolean shadow;
	private Long costume;
	private Long region;
	private Long total;

	public PokemonDao() {
	}
	
	public PokemonDao(final Long id, final Long dexid, final String name, boolean lucky,
		boolean shiny, boolean shadow, Long costume, Long region) {
		this.id=id;
		this.dexid=dexid;
		this.name=name;
		this.done=true;
		this.lucky=lucky;
		this.shiny=shiny;
		this.shadow=shadow;
		this.costume=costume;
		this.region=region;
		this.total=1L;
	}

	public static PokemonDao fromEntity(PokemonEntity pokemon) {
		return new PokemonDao(pokemon.getId(), pokemon.getDexid(),
		    pokemon.getName(), pokemon.getLucky(),
		    pokemon.getShiny(), pokemon.getShadow(),
		    pokemon.getCostume(), pokemon.getRegion());
	}

}
