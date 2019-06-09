package com.clumsy.luckylister.data;

import com.clumsy.luckylister.entities.UserEntity;

import lombok.Data;

@Data
public class FriendDao {

	private Long id;
	private String displayName;
	private boolean friends;

	public FriendDao() {
	}
	
	public FriendDao(final Long id, final String displayName, final boolean friends) {
		this.id=id;
		this.displayName=displayName;
		this.friends=friends;
	}

	public static FriendDao fromEntity(UserEntity user, boolean friends) {
		return new FriendDao(user.getId(), user.getDisplayName(), friends);
	}
}
