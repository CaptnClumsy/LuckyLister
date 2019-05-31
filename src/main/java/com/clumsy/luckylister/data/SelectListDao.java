package com.clumsy.luckylister.data;

import com.clumsy.luckylister.entities.UserEntity;

import lombok.Data;

@Data
public class SelectListDao {
	
	private Long id;
	private String text;
	
	public SelectListDao() {
	}

	public SelectListDao(final Long id, final String name) {
		this.id=id;
		this.text=name;
	}

	public static SelectListDao fromEntity(UserEntity user) {
		return new SelectListDao(user.getId(), user.getDisplayName());
	}

}