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
@Table(name = "user_hundo_pokemon")
public class UserHundoPokemonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "userid")
	private Long userid;

	@Column(name = "pokemonid")
	private Long pokemonid;

	@Column(name = "total")
	private Long total;

}
