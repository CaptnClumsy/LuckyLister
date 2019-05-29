package com.clumsy.luckylister.repos;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clumsy.luckylister.entities.UserLuckyPokemonEntity;

@Repository
public interface UserLuckyPokemonRepo extends JpaRepository<UserLuckyPokemonEntity, Long> {
	@Query("SELECT t.pokemonid FROM UserLuckyPokemonEntity t WHERE t.userid = ?1")
	Set<Long> findByUserId(Long id);

	@Query("SELECT t FROM UserLuckyPokemonEntity t WHERE t.userid = ?1 AND t.pokemonid = ?2")
	UserLuckyPokemonEntity findByUserIdAndPokemonId(Long id, Long pokemonId);
}
