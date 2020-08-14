package com.clumsy.luckylister.data;

import com.clumsy.luckylister.entities.PokemonEntity;

import lombok.Data;

@Data
public class PokemonSelectListDao {

	private Long id;
	private Long costume;
	private boolean shadow;
	private Long region;
	private String text;

	public PokemonSelectListDao() {
	}

	public PokemonSelectListDao(final Long id, final String name,
			final Long costume, final boolean shadow, final Long region) {
		this.id=id;
		this.text=name;
		this.costume=costume;
		this.shadow=shadow;
		this.region=region;
	}

	public static PokemonSelectListDao fromPokemonEntity(PokemonEntity mon) {
		return new PokemonSelectListDao(mon.getId(), mon.getName(), mon.getCostume(), mon.getShadow(), mon.getRegion());
	}
}
