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

	@Column(name = "lucky")
	private Boolean lucky;
	
	@Column(name = "shiny")
	private Boolean shiny;
	
	@Column(name = "shadow")
	private Boolean shadow;
	
	@Column(name = "costume")
	private Long costume;
	
	@Column(name = "variant")
	private Long variant;
	
	@Column(name = "region")
	private Long region;

}
