package com.clumsy.luckylister.data;

import lombok.Data;

@Data
public class UpdateFriendDao {

	private boolean friends;
	
	public UpdateFriendDao() {
	}
	
	public UpdateFriendDao(final boolean friends) {
		this.friends=friends;
	}

}
