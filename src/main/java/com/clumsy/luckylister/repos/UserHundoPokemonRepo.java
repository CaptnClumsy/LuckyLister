package com.clumsy.luckylister.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clumsy.luckylister.entities.UserHundoPokemonEntity;

@Repository
public interface UserHundoPokemonRepo extends JpaRepository<UserHundoPokemonEntity, Long> {
	@Query("SELECT t FROM UserHundoPokemonEntity t WHERE t.userid = ?1")
	List<UserHundoPokemonEntity> findByUserId(Long id);

	@Query("SELECT t FROM UserHundoPokemonEntity t WHERE t.userid = ?1 AND t.pokemonid = ?2")
	UserHundoPokemonEntity findByUserIdAndPokemonId(Long id, Long pokemonId);
}
