package com.clumsy.luckylister.data;

import lombok.Data;

@Data
public class PrefsDao {

	private boolean costumes;

	public PrefsDao() {
	}
	
	public PrefsDao(final boolean costumes) {
		this.costumes=costumes;
	}

}