package com.clumsy.luckylister.repos;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clumsy.luckylister.entities.FriendEntity;

@Repository
public interface FriendRepo extends JpaRepository<FriendEntity, Long> {

	@Query("SELECT t FROM FriendEntity t WHERE t.userid = ?1 AND t.friendid = ?2")
	FriendEntity findFriend(Long id, Long friendId);

	@Query("SELECT t.friendid FROM FriendEntity t WHERE t.userid = ?1")
	Set<Long> findAllByUser(Long id);
	
}
