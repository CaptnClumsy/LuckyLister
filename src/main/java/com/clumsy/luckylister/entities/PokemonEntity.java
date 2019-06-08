package com.clumsy.luckylister.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "pokemon")
public class PokemonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "dexid")
	private Long dexid;

	@Column(name = "genid")
	private Long genid;

	@Column(name = "name")
	private String name;

	@Column(name = "available")
	private Boolean available;

}
