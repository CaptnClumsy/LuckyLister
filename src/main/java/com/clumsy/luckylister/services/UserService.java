package com.clumsy.luckylister.services;

import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clumsy.luckylister.data.LeaderDao;
import com.clumsy.luckylister.data.TotalDao;
import com.clumsy.luckylister.entities.LeaderEntity;
import com.clumsy.luckylister.entities.UserEntity;
import com.clumsy.luckylister.exceptions.UserNotFoundException;
import com.clumsy.luckylister.repos.UserRepo;

@Service
public class UserService {

	private static final Long DEFAULT_USERID = 0L;

	private final UserRepo userRepo;
	
	@Autowired
	UserService(final UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	@Transactional(readOnly = true)
	public UserEntity getDefaultAccount() throws UserNotFoundException {
		Optional<UserEntity> user = userRepo.findById(DEFAULT_USERID);
		if (user == null) {
			throw new UserNotFoundException("Default account does not exist");
		}
		return user.get();
	}

	@Transactional
	public UserEntity getCurrentUser(final Principal principal) throws UserNotFoundException {
		if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
			return getDefaultAccount();
		}
		UserEntity user = userRepo.findOneByName(principal.getName());
		if (user == null) {
			// Automatically register new users
			UserEntity newUser = new UserEntity();
			newUser.setName(principal.getName());
			newUser.setAdmin(false);
			if (principal instanceof OAuth2Authentication) {
	        	OAuth2Authentication auth = (OAuth2Authentication)principal;
	        	@SuppressWarnings("unchecked")
				LinkedHashMap<String,String> details = (LinkedHashMap<String, String>) auth.getUserAuthentication().getDetails();
	        	newUser.setDisplayName(details.get("name"));
	        } else {
	        	newUser.setDisplayName("none");
	        }
			UserEntity savedUser = userRepo.save(newUser);
			return savedUser;
		}
		return user;
	}

	@Transactional(readOnly = true)
	public List<UserEntity> getAllUsers() throws UserNotFoundException {
		List<UserEntity> users = userRepo.findAll();
		if (users == null) {
			throw new UserNotFoundException("No users found");
		}
		return users;
	}

	@Transactional(readOnly = true)
	public UserEntity getUser(Long userId) throws UserNotFoundException {
		UserEntity user = userRepo.getOne(userId);
		if (user == null) {
			throw new UserNotFoundException("User not found");
	    }
		return user;
	}

	@Transactional(readOnly = true)
	public TotalDao getStats(UserEntity user) {
		Long total = userRepo.findTotal();
		Long amount = userRepo.findLucky(user.getId());
		return new TotalDao(total, amount);
	}

	@Transactional(readOnly = true)
	public List<LeaderDao> getLeaderboard(UserEntity user) {
		final List<LeaderEntity> leaderEntities = userRepo.findLeaders();
		final List<LeaderDao> leaders = new ArrayList<>(leaderEntities.size());
		int rank = 1;
		for (LeaderEntity leaderEntity : leaderEntities) {
			LeaderDao leader = new LeaderDao(rank++, leaderEntity.getDisplayName(), leaderEntity.getTotal());
			leaders.add(leader);
		}
		return leaders;
	}

}

