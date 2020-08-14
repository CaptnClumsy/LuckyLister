package com.clumsy.luckylister.data;

import com.clumsy.luckylister.entities.FilterEntity;

import lombok.Data;

@Data
public class FilterDao {

	private Boolean shiny_costumes;
	private Boolean shiny_shadows;
	private Boolean shiny_alolan;
	private Boolean shiny_other;
	private Boolean lucky_costumes;
	private Boolean lucky_alolan;
	private Boolean lucky_other;
	private Boolean hundo_costumes;
	private Boolean hundo_shadows;
	private Boolean hundo_alolan;
	private Boolean hundo_other;

	public FilterDao() {
	}
	
	public FilterDao(final boolean shiny_costumes, final boolean shiny_shadows, final boolean shiny_alolan, final boolean shiny_other,
			final boolean lucky_costumes, final boolean lucky_alolan, final boolean lucky_other,
			final boolean hundo_costumes, final boolean hundo_shadows, final boolean hundo_alolan, final boolean hundo_other) {
		this.shiny_costumes=shiny_costumes;
		this.shiny_shadows=shiny_shadows;
		this.shiny_alolan=shiny_alolan;
		this.shiny_other=shiny_other;
		this.lucky_costumes=lucky_costumes;
		this.lucky_alolan=lucky_alolan;
		this.lucky_other=lucky_other;
		this.hundo_costumes=hundo_costumes;
		this.hundo_shadows=hundo_shadows;
		this.hundo_alolan=hundo_alolan;
		this.hundo_other=hundo_other;
	}

	public static FilterDao fromEntity(FilterEntity filter) {
		return new FilterDao(filter.getShiny_costumes(), filter.getShiny_shadows(), filter.getShiny_alolan(), filter.getShiny_other(),
				filter.getLucky_costumes(), filter.getLucky_alolan(), filter.getLucky_other(),
				filter.getHundo_costumes(), filter.getHundo_shadows(), filter.getHundo_alolan(), filter.getHundo_other());
	}
}