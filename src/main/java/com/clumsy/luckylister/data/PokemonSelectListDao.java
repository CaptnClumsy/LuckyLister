package com.clumsy.luckylister.data;

import com.clumsy.luckylister.entities.PokemonEntity;

import lombok.Data;

@Data
public class PokemonSelectListDao {

	private Long id;
	private Long dexid;
	private Long costume;
	private boolean shadow;
	private boolean shiny;
	private Long variant;
	private Long region;
	private String text;

	public PokemonSelectListDao() {
	}

	public PokemonSelectListDao(final Long id, final Long dexid, final String name,
			final Long costume, final boolean shiny, final boolean shadow, 
			final Long variant, final Long region) {
		this.id=id;
	    this.dexid=dexid;
		this.text=name;
		this.costume=costume;
		this.shiny=shiny;
		this.shadow=shadow;
		this.variant=variant;
		this.region=region;
	}

	public static PokemonSelectListDao fromPokemonEntity(PokemonEntity mon) {
		return new PokemonSelectListDao(mon.getId(), mon.getDexid(), mon.getName(),
			mon.getCostume(), mon.getShiny(), mon.getShadow(), mon.getVariant(), mon.getRegion());
	}
}
