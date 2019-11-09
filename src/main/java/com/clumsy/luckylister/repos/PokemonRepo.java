package com.clumsy.luckylister.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clumsy.luckylister.entities.PokemonEntity;

@Repository
public interface PokemonRepo extends JpaRepository<PokemonEntity, Long> {
	PokemonEntity findOneByName(String name);

	@Query("SELECT t FROM PokemonEntity t WHERE t.lucky=true AND t.id NOT IN (SELECT t1.pokemonid FROM UserLuckyPokemonEntity t1 WHERE t1.userid=?1) ORDER BY t.dexid ASC")
	List<PokemonEntity> findAllForUser(Long userId);
	
	@Query("SELECT t FROM PokemonEntity t WHERE t.lucky=true ORDER BY t.dexid ASC")
	List<PokemonEntity> findAll();

	@Query("SELECT t FROM PokemonEntity t WHERE t.shiny=true ORDER BY t.dexid ASC, t.region ASC, t.costume ASC")
	List<PokemonEntity> findAllShiny();
	
	@Query("SELECT t FROM PokemonEntity t WHERE t.shadow=true ORDER BY t.dexid ASC, t.region ASC, t.costume ASC")
	List<PokemonEntity> findAllShadow();
	
	@Query("SELECT t FROM PokemonEntity t WHERE t.shiny=true AND t.id NOT IN (SELECT t1.pokemonid FROM UserShinyPokemonEntity t1 "
			+ "WHERE t1.userid=?1) ORDER BY t.dexid ASC, t.region ASC, t.costume ASC")
	List<PokemonEntity> findAllShinyForUser(Long userId);
	
	@Query("SELECT t FROM PokemonEntity t WHERE t.shadow=true AND t.id NOT IN (SELECT t1.pokemonid FROM UserShadowPokemonEntity t1 "
			+ "WHERE t1.userid=?1) ORDER BY t.dexid ASC, t.region ASC, t.costume ASC")
	List<PokemonEntity> findAllShadowForUser(Long userId);
	
	@Query("SELECT COUNT(*) FROM PokemonEntity t WHERE t.shiny=true AND t.dexid=?1")
	Integer countAllShinyByDexId(Long dexId);
	
	@Query("SELECT t FROM PokemonEntity t WHERE t.shiny=false AND t.shadow=false AND t.id NOT IN (SELECT t1.pokemonid FROM UserHundoPokemonEntity t1 "
			+ "WHERE t1.userid=?1) ORDER BY t.dexid ASC, t.region ASC, t.costume ASC")
	List<PokemonEntity> findAllHundoForUser(Long userId);
	
	@Query("SELECT t FROM PokemonEntity t WHERE t.shiny=false AND t.shadow=false ORDER BY t.dexid ASC")
	List<PokemonEntity> findAllHundos();
}
