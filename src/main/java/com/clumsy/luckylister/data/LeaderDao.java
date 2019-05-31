package com.clumsy.luckylister.data;

import lombok.Data;

@Data
public class LeaderDao implements Comparable<LeaderDao> {
	private Integer rank;
	private String name;
	private Integer total;

	public LeaderDao(final Integer rank, final String name, final Integer total) {
		this.rank=rank;
		this.name=name;
		this.total=total;
	}

	@Override
	public int compareTo(LeaderDao o) {
	    if (o.getTotal() > total) {
	        return +1;
		} else if (o.getTotal() < total) {
		    return -1;
		} 
		return 0;
	}
}