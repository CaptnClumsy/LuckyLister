package com.clumsy.luckylister.repos;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clumsy.luckylister.entities.UserShinyPokemonEntity;

@Repository
public interface UserShinyPokemonRepo extends JpaRepository<UserShinyPokemonEntity, Long> {
	@Query("SELECT t.pokemonid FROM UserShinyPokemonEntity t WHERE t.userid = ?1")
	Set<Long> findByUserId(Long id);

	@Query("SELECT t FROM UserShinyPokemonEntity t WHERE t.userid = ?1 AND t.pokemonid = ?2")
	UserShinyPokemonEntity findByUserIdAndPokemonId(Long id, Long pokemonId);
}
