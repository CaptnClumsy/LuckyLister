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
@Table(name = "user_filters")
public class FilterEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "userid")
	private Long userid;

	@Column(name = "shiny_costumes")
	private Boolean shiny_costumes;
	@Column(name = "shiny_shadows")
	private Boolean shiny_shadows;
	@Column(name = "shiny_alolan")
	private Boolean shiny_alolan;
	@Column(name = "shiny_other")
	private Boolean shiny_other;
	
	@Column(name = "lucky_costumes")
	private Boolean lucky_costumes;
	@Column(name = "lucky_alolan")
	private Boolean lucky_alolan;
	@Column(name = "lucky_other")
	private Boolean lucky_other;
	
	@Column(name = "hundo_costumes")
	private Boolean hundo_costumes;
	@Column(name = "hundo_shadows")
	private Boolean hundo_shadows;
	@Column(name = "hundo_alolan")
	private Boolean hundo_alolan;
	@Column(name = "hundo_other")
	private Boolean hundo_other;
}
