package com.clumsy.luckylister.repos;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clumsy.luckylister.entities.UserShadowPokemonEntity;

@Repository
public interface UserShadowPokemonRepo extends JpaRepository<UserShadowPokemonEntity, Long> {
	@Query("SELECT t.pokemonid FROM UserShadowPokemonEntity t WHERE t.userid = ?1")
	Set<Long> findByUserId(Long id);

	@Query("SELECT t FROM UserShadowPokemonEntity t WHERE t.userid = ?1 AND t.pokemonid = ?2")
	UserShadowPokemonEntity findByUserIdAndPokemonId(Long id, Long pokemonId);
}
