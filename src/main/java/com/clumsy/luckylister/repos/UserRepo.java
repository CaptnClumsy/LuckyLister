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
	
	@Query("SELECT COUNT(*) FROM PokemonEntity t WHERE t.lucky=true AND t.costume!=0")
	Long findLuckyCostumeTotal();
	@Query("SELECT COUNT(*) FROM PokemonEntity t WHERE t.lucky=true AND t.region!=0")
	Long findLuckyAlolanTotal();
	@Query("SELECT COUNT(*) FROM PokemonEntity t WHERE t.lucky=true AND t.costume=0 AND t.region=0")
	Long findTotal();
	
	@Query("SELECT COUNT(*) FROM UserLuckyPokemonEntity t, PokemonEntity p WHERE t.userid=?1 AND p.id=t.pokemonid AND p.costume!=0")
	Long findLuckyCostume(Long userid);
	@Query("SELECT COUNT(*) FROM UserLuckyPokemonEntity t, PokemonEntity p WHERE t.userid=?1 AND p.id=t.pokemonid AND p.region!=0")
	Long findLuckyAlolan(Long userid);
	@Query("SELECT COUNT(*) FROM UserLuckyPokemonEntity t, PokemonEntity p WHERE t.userid=?1 AND p.id=t.pokemonid AND p.costume=0 AND p.region=0")
	Long findLucky(Long userid);
	
	@Query("SELECT u.id AS id, u.displayName AS displayName, COUNT(*) AS total " +
			"FROM UserEntity u, UserLuckyPokemonEntity l, PokemonEntity p WHERE l.userid=u.id " +
			"AND p.id=l.pokemonid AND p.region=0 AND p.costume=0 " +
			"GROUP BY u.id ORDER BY total DESC, displayName DESC")
	List<LeaderEntity> findLeaders();

	UserEntity findOneByDisplayName(String displayName);

	@Query("SELECT t FROM UserEntity t WHERE t.id != 0 AND " +
			" t.id NOT IN (" +
			  "SELECT l.userid FROM UserLuckyPokemonEntity l WHERE " +
			  "l.pokemonid=?1) " +
			"ORDER BY t.displayName ASC")
	List<UserEntity> findAllByPokemonId(Long pokemonId);
	
	@Query("SELECT COUNT(*) FROM UserShinyPokemonEntity t, PokemonEntity p WHERE t.userid=?1 AND p.id=t.pokemonid AND p.costume!=0")
	Long findShinyCostume(Long userid);
	@Query("SELECT COUNT(*) FROM UserShinyPokemonEntity t, PokemonEntity p WHERE t.userid=?1 AND p.id=t.pokemonid AND p.shadow=true")
	Long findShinyShadow(Long userid);
	@Query("SELECT COUNT(*) FROM UserShinyPokemonEntity t, PokemonEntity p WHERE t.userid=?1 AND p.id=t.pokemonid AND p.region!=0")
	Long findShinyAlolan(Long userid);
	@Query("SELECT COUNT(*) FROM UserShinyPokemonEntity t, PokemonEntity p WHERE t.userid=?1 AND p.id=t.pokemonid AND p.costume=0 AND p.shadow=false AND p.region=0")
	Long findShiny(Long userid);
	
	@Query("SELECT COUNT(*) FROM PokemonEntity t WHERE t.shiny=true AND t.costume!=0")
	Long findShinyCostumeTotal();
	@Query("SELECT COUNT(*) FROM PokemonEntity t WHERE t.shiny=true AND t.shadow=true")
	Long findShinyShadowTotal();
	@Query("SELECT COUNT(*) FROM PokemonEntity t WHERE t.shiny=true AND t.region!=0")
	Long findShinyAlolanTotal();
	@Query("SELECT COUNT(*) FROM PokemonEntity t WHERE t.shiny=true AND t.costume=0 AND t.shadow=false AND t.region=0")
	Long findShinyTotal();
	
	@Query("SELECT COUNT(*) FROM UserShadowPokemonEntity t, PokemonEntity p WHERE t.userid=?1 AND p.id=t.pokemonid AND p.shadow=true AND p.shiny=false")
	Long findShadow(Long userid);
	
	@Query("SELECT COUNT(*) FROM PokemonEntity t WHERE t.shadow=true AND t.shiny=false")
	Long findShadowTotal();
	
	@Query("SELECT u.id AS id, u.displayName AS displayName, COUNT(*) AS total " +
			"FROM UserEntity u, UserShinyPokemonEntity p WHERE p.userid=u.id " + 
			"GROUP BY u.id ORDER BY total DESC, displayName DESC")
	List<LeaderEntity> findShinyLeaders();
	
	@Query("SELECT u.id AS id, u.displayName AS displayName, COUNT(*) AS total " +
			"FROM UserEntity u, UserShadowPokemonEntity p WHERE p.userid=u.id " + 
			"GROUP BY u.id ORDER BY total DESC, displayName DESC")
	List<LeaderEntity> findShadowLeaders();
	
	@Query("SELECT l.userid AS id, COUNT(*) AS count FROM UserShinyPokemonEntity l WHERE " +
			  "l.pokemonid IN (SELECT p.id FROM PokemonEntity p WHERE p.dexid=?1 AND p.shiny=true) GROUP BY l.userid ORDER BY l.userid")
	List<UserShinyCountEntity> findAllShinyByDexId(Long dexId);
	
	@Query("SELECT t FROM UserEntity t WHERE t.id != 0 AND " +
			" t.id NOT IN (" +
			  "SELECT l.userid FROM UserHundoPokemonEntity l WHERE " +
			  "l.pokemonid=?1) " +
			"ORDER BY t.displayName ASC")
	List<UserEntity> findAllHundoByPokemonId(Long pokemonId);
	
	@Query("SELECT t FROM UserEntity t WHERE t.id != 0 AND " +
			" t.id NOT IN (" +
			  "SELECT l.userid FROM UserShadowPokemonEntity l WHERE " +
			  "l.pokemonid=?1) " +
			"ORDER BY t.displayName ASC")
	List<UserEntity> findAllShadowByPokemonId(Long pokemonId);
	
	@Query("SELECT COUNT(*) FROM UserHundoPokemonEntity t, PokemonEntity p WHERE t.userid=?1 AND p.id=t.pokemonid AND p.costume=0 AND p.region=0 AND p.shadow=false")
	Long findHundo(Long userid);
	@Query("SELECT COUNT(*) FROM UserHundoPokemonEntity t, PokemonEntity p WHERE t.userid=?1 AND p.id=t.pokemonid AND p.costume!=0")
	Long findHundoCostume(Long userid);
	@Query("SELECT COUNT(*) FROM UserHundoPokemonEntity t, PokemonEntity p WHERE t.userid=?1 AND p.id=t.pokemonid AND p.shadow=true")
	Long findHundoShadow(Long userid);
	@Query("SELECT COUNT(*) FROM UserHundoPokemonEntity t, PokemonEntity p WHERE t.userid=?1 AND p.id=t.pokemonid AND p.region!=0")
	Long findHundoAlolan(Long userid);
	
	@Query("SELECT COUNT(*) FROM PokemonEntity t WHERE t.shiny=false AND t.region=0 AND t.costume=0 AND t.shadow=false")
	Long findHundoTotal();
	@Query("SELECT COUNT(*) FROM PokemonEntity t WHERE t.shiny=false AND t.costume!=0")
	Long findHundoCostumeTotal();
	@Query("SELECT COUNT(*) FROM PokemonEntity t WHERE t.shiny=false AND t.shadow=true")
	Long findHundoShadowTotal();
	@Query("SELECT COUNT(*) FROM PokemonEntity t WHERE t.shiny=false AND t.region!=0")
	Long findHundoAlolanTotal();
	
	@Query("SELECT u.id AS id, u.displayName AS displayName, COUNT(*) AS total " +
			"FROM UserEntity u, UserHundoPokemonEntity p WHERE p.userid=u.id " + 
			"GROUP BY u.id ORDER BY total DESC, displayName DESC")
	List<LeaderEntity> findHundoLeaders();
	
	@Query("SELECT u.id AS id, u.displayName AS displayName, SUM(p.total) AS total " +
			"FROM UserEntity u, UserHundoPokemonEntity p WHERE p.userid=u.id " + 
			"GROUP BY u.id ORDER BY total DESC, displayName DESC")
	List<LeaderEntity> findHundoCountLeaders();

	@Query("SELECT SUM(t.total) FROM UserHundoPokemonEntity t, PokemonEntity p WHERE t.userid=?1 AND p.id=t.pokemonid AND p.region=0 AND p.costume=0 AND p.shadow=false")
	Long findHundoCount(Long userid);
	@Query("SELECT SUM(t.total) FROM UserHundoPokemonEntity t, PokemonEntity p WHERE t.userid=?1 AND p.id=t.pokemonid AND p.costume!=0")
	Long findHundoCostumeCount(Long userid);
	@Query("SELECT SUM(t.total) FROM UserHundoPokemonEntity t, PokemonEntity p WHERE t.userid=?1 AND p.id=t.pokemonid AND p.shadow=true")
	Long findHundoShadowCount(Long userid);
	@Query("SELECT SUM(t.total) FROM UserHundoPokemonEntity t, PokemonEntity p WHERE t.userid=?1 AND p.id=t.pokemonid AND p.region!=0")
	Long findHundoAlolanCount(Long userid);

	
	@Query("SELECT t FROM UserEntity t WHERE t.id != 0 AND " +
			" t.id NOT IN (" +
			  "SELECT l.userid FROM UserNinetyEightPokemonEntity l WHERE " +
			  "l.pokemonid=?1) " +
			"ORDER BY t.displayName ASC")
	List<UserEntity> findAllNinetyEightByPokemonId(Long pokemonId);
	
	@Query("SELECT COUNT(*) FROM UserNinetyEightPokemonEntity t WHERE t.userid=?1")
	Long findNinetyEight(Long userid);
	
	@Query("SELECT COUNT(*) FROM PokemonEntity t WHERE t.shiny=false AND t.shadow=false")
	Long findNinetyEightTotal();
	
	@Query("SELECT u.id AS id, u.displayName AS displayName, COUNT(*) AS total " +
			"FROM UserEntity u, UserNinetyEightPokemonEntity p WHERE p.userid=u.id " + 
			"GROUP BY u.id ORDER BY total DESC, displayName DESC")
	List<LeaderEntity> findNinetyEightLeaders();
	
	@Query("SELECT u.id AS id, u.displayName AS displayName, SUM(p.total) AS total " +
			"FROM UserEntity u, UserNinetyEightPokemonEntity p WHERE p.userid=u.id " + 
			"GROUP BY u.id ORDER BY total DESC, displayName DESC")
	List<LeaderEntity> findNinetyEightCountLeaders();

	@Query("SELECT SUM(total) FROM UserNinetyEightPokemonEntity t WHERE t.userid=?1")
	Long findNinetyEightCount(Long userid);
}