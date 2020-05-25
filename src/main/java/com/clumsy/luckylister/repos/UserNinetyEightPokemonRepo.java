package com.clumsy.luckylister.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clumsy.luckylister.entities.UserNinetyEightPokemonEntity;

@Repository
public interface UserNinetyEightPokemonRepo extends JpaRepository<UserNinetyEightPokemonEntity, Long> {
	@Query("SELECT t FROM UserNinetyEightPokemonEntity t WHERE t.userid = ?1")
	List<UserNinetyEightPokemonEntity> findByUserId(Long id);

	@Query("SELECT t FROM UserNinetyEightPokemonEntity t WHERE t.userid = ?1 AND t.pokemonid = ?2")
	UserNinetyEightPokemonEntity findByUserIdAndPokemonId(Long id, Long pokemonId);
}
