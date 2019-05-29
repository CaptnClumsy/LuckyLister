package com.clumsy.luckylister.data;

import lombok.Data;

@Data
public class UpdatePokemonDao {

	private boolean selected;
	
	public UpdatePokemonDao() {
	}
	
	public UpdatePokemonDao(final boolean selected) {
		this.selected=selected;
	}

}
