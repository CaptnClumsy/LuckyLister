package com.clumsy.luckylister.data;

import lombok.Data;

@Data
public class TotalDao {

	private Long total;
	private Long amount;
	private Long count;
	
	public TotalDao() {
	}

	public TotalDao(final Long total, final Long amount) {
		this.total=total;
		this.amount=amount;
		this.count=0L;
	}

	public TotalDao(final Long total, final Long amount, Long count) {
		this.total=total;
		this.amount=amount;
		this.count=count;
	}
}
