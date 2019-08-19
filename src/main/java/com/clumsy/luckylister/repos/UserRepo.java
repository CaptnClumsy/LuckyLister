package com.clumsy.luckylister.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clumsy.luckylister.entities.LeaderEntity;
import com.clumsy.luckylister.entities.UserEntity;
import com.clumsy.luckylister.entities.UserShinyCountEntity;
 
@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
	UserEntity findOneByName(String name);
	
	@Query("SELECT t FROM UserEntity t WHERE t.id != 0 ORDER BY t.displayName ASC")
	List<UserEntity> findAll();
	
	@Query("SELECT COUNT(*) FROM PokemonEntity t WHERE t.lucky=true")
	Long findTotal();
	
	@Query("SELECT COUNT(*) FROM UserLuckyPokemonEntity t WHERE t.userid=?1")
	Long findLucky(Long userid);
	
	@Query("SELECT u.id AS id, u.displayName AS displayName, COUNT(*) AS total " +
			"FROM UserEntity u, UserLuckyPokemonEntity p WHERE p.userid=u.id " + 
			"GROUP BY u.id ORDER BY total DESC, displayName DESC")
	List<LeaderEntity> findLeaders();

	UserEntity findOneByDisplayName(String displayName);

	@Query("SELECT t FROM UserEntity t WHERE t.id != 0 AND " +
			" t.id NOT IN (" +
			  "SELECT l.userid FROM UserLuckyPokemonEntity l WHERE " +
			  "l.pokemonid=?1) " +
			"ORDER BY t.displayName ASC")
	List<UserEntity> findAllByPokemonId(Long pokemonId);
	
	@Query("SELECT COUNT(*) FROM UserShinyPokemonEntity t WHERE t.userid=?1")
	Long findShiny(Long userid);
	
	@Query("SELECT COUNT(*) FROM PokemonEntity t WHERE t.shiny=true")
	Long findShinyTotal();
	
	@Query("SELECT u.id AS id, u.displayName AS displayName, COUNT(*) AS total " +
			"FROM UserEntity u, UserShinyPokemonEntity p WHERE p.userid=u.id " + 
			"GROUP BY u.id ORDER BY total DESC, displayName DESC")
	List<LeaderEntity> findShinyLeaders();
	
	@Query("SELECT l.userid AS id, COUNT(*) AS count FROM UserShinyPokemonEntity l WHERE " +
			  "l.pokemonid IN (SELECT p.id FROM PokemonEntity p WHERE p.dexid=?1 AND p.shiny=true) GROUP BY l.userid ORDER BY l.userid")
	List<UserShinyCountEntity> findAllShinyByDexId(Long dexId);
	
	@Query("SELECT t FROM UserEntity t WHERE t.id != 0 AND " +
			" t.id NOT IN (" +
			  "SELECT l.userid FROM UserHundoPokemonEntity l WHERE " +
			  "l.pokemonid=?1) " +
			"ORDER BY t.displayName ASC")
	List<UserEntity> findAllHundoByPokemonId(Long pokemonId);
	
	@Query("SELECT COUNT(*) FROM UserHundoPokemonEntity t WHERE t.userid=?1")
	Long findHundo(Long userid);
	
	@Query("SELECT COUNT(*) FROM PokemonEntity t WHERE t.shiny=false")
	Long findHundoTotal();
	
	@Query("SELECT u.id AS id, u.displayName AS displayName, COUNT(*) AS total " +
			"FROM UserEntity u, UserHundoPokemonEntity p WHERE p.userid=u.id " + 
			"GROUP BY u.id ORDER BY total DESC, displayName DESC")
	List<LeaderEntity> findHundoLeaders();
	
	@Query("SELECT u.id AS id, u.displayName AS displayName, SUM(p.total) AS total " +
			"FROM UserEntity u, UserHundoPokemonEntity p WHERE p.userid=u.id " + 
			"GROUP BY u.id ORDER BY total DESC, displayName DESC")
	List<LeaderEntity> findHundoCountLeaders();

	@Query("SELECT SUM(total) FROM UserHundoPokemonEntity t WHERE t.userid=?1")
	Long findHundoCount(Long userid);

}