package com.clumsy.luckylister.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clumsy.luckylister.entities.PokemonEntity;

@Repository
public interface PokemonRepo extends JpaRepository<PokemonEntity, Long> {
	PokemonEntity findOneByName(String name);
}
