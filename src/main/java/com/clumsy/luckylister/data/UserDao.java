package com.clumsy.luckylister.data;

import com.clumsy.luckylister.entities.UserEntity;

import lombok.Data;

@Data
public class UserDao {
	
	private Long id;
	private String name;
	private String displayName;
	private boolean admin;

	public UserDao() {
	}
	
	public UserDao(final Long id, final String name, final String displayName,
		final boolean admin) {
		this.id=id;
		this.name=name;
		this.displayName=displayName;
		this.admin=admin;
	}

	public static UserDao fromEntity(UserEntity user) {
		return new UserDao(user.getId(), user.getName(), user.getDisplayName(),
			user.getAdmin());
	}

}
