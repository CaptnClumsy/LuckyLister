package com.clumsy.luckylister.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clumsy.luckylister.entities.LeaderEntity;
import com.clumsy.luckylister.entities.UserEntity;
 
@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
	UserEntity findOneByName(String name);
	
	@Query("SELECT t FROM UserEntity t WHERE t.id != 0 ORDER BY t.displayName ASC")
	List<UserEntity> findAll();
	
	@Query("SELECT COUNT(*) FROM PokemonEntity t WHERE t.available=true")
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
}