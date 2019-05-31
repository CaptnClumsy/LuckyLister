package com.clumsy.luckylister.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clumsy.luckylister.entities.PokemonEntity;

@Repository
public interface PokemonRepo extends JpaRepository<PokemonEntity, Long> {
	PokemonEntity findOneByName(String name);

	@Query("SELECT t FROM PokemonEntity t WHERE t.id NOT IN (SELECT t1.pokemonid FROM UserLuckyPokemonEntity t1 WHERE t1.userid=?1) ORDER BY t.dexid ASC")
	List<PokemonEntity> findAllForUser(Long userId);
}
