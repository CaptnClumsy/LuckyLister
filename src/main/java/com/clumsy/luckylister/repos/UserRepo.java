package com.clumsy.luckylister.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clumsy.luckylister.entities.UserEntity;
 
@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
	UserEntity findOneByName(String name);
}