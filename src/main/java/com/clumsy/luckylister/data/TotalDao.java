package com.clumsy.luckylister.data;

import lombok.Data;

@Data
public class TotalDao {

	private Long total;
	private Long amount;
	
	public TotalDao() {
	}

	public TotalDao(final Long total, final Long amount) {
		this.total=total;
		this.amount=amount;
	}

}
