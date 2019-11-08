package com.clumsy.luckylister.services;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clumsy.luckylister.data.FriendDao;
import com.clumsy.luckylister.data.LeaderDao;
import com.clumsy.luckylister.data.TotalDao;
import com.clumsy.luckylister.data.UserDao;
import com.clumsy.luckylister.entities.FriendEntity;
import com.clumsy.luckylister.entities.LeaderEntity;
import com.clumsy.luckylister.entities.UserEntity;
import com.clumsy.luckylister.entities.UserShinyCountEntity;
import com.clumsy.luckylister.exceptions.UserAlreadyRegisteredException;
import com.clumsy.luckylister.exceptions.UserNotFoundException;
import com.clumsy.luckylister.repos.FriendRepo;
import com.clumsy.luckylister.repos.PokemonRepo;
import com.clumsy.luckylister.repos.UserRepo;

@Service
public class UserService {

	private static final Long DEFAULT_USERID = 0L;

	private final UserRepo userRepo;
	private final FriendRepo friendRepo;
	private final PokemonRepo pokemonRepo;
	
	@Autowired
	UserService(final UserRepo userRepo, final FriendRepo friendRepo, final PokemonRepo pokemonRepo) {
		this.userRepo = userRepo;
		this.friendRepo = friendRepo;
		this.pokemonRepo = pokemonRepo;
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
	public UserEntity getCurrentUser(final Principal principal) throws UserNotFoundException, UserAlreadyRegisteredException {
		if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated() || principal == null) {
			return getDefaultAccount();
		}
		UserEntity user = userRepo.findOneByName(principal.getName());
		if (user == null) {
			String displayName = "none";
			// Get this users display name
			if (principal instanceof OAuth2Authentication) {
	        	OAuth2Authentication auth = (OAuth2Authentication)principal;
	        	@SuppressWarnings("unchecked")
				LinkedHashMap<String,String> details = (LinkedHashMap<String, String>) auth.getUserAuthentication().getDetails();
	        	displayName = details.get("name");
	        }
			// Check nobody with this name is already registered
			UserEntity dupUser = userRepo.findOneByDisplayName(displayName);
			if (dupUser!=null) {
				throw new UserAlreadyRegisteredException("User with name "+displayName+" already exists");
			}
			// Automatically register the new user
			UserEntity newUser = new UserEntity();
			newUser.setName(principal.getName());
			newUser.setDisplayName(displayName);
			newUser.setAdmin(false);
			newUser.setCostumes(true);
			
			UserEntity savedUser = userRepo.save(newUser);
			return savedUser;
		}
		return user;
	}

	@Transactional(readOnly = true)
	public List<UserDao> getAllUsers(UserEntity user) throws UserNotFoundException {
		List<UserEntity> users = userRepo.findAll();
		if (users == null) {
			throw new UserNotFoundException("No users found");
		}
		final Set<Long> allFriends = friendRepo.findAllByUser(user.getId());
		List<UserDao> userDaos = new ArrayList<>(users.size());
		for (UserEntity thisUser : users) {
			UserDao userDao = UserDao.fromEntity(thisUser);
			if (allFriends!=null && allFriends.contains(thisUser.getId())) {
				userDao.setFriends(true);
			}
			userDaos.add(userDao);
		}
		Collections.sort(userDaos, (u1, u2) -> {
			if (u1.isFriends() == u2.isFriends()) {
				return u1.getDisplayName().compareTo(u2.getDisplayName());
			}
			return (u1.isFriends() ? -1 : 1);
		});
		return userDaos;
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

	@Transactional(readOnly = true)
	public List<UserDao> getAllUsersWithPokemon(UserEntity user, Long pokemonId) {
		final List<UserEntity> users = userRepo.findAllByPokemonId(pokemonId);
		if (users == null) {
			return Collections.emptyList();
		}
		final Set<Long> allFriends = friendRepo.findAllByUser(user.getId());
		List<UserDao> userDaos = new ArrayList<>(users.size());
		for (UserEntity thisUser : users) {
			UserDao userDao = UserDao.fromEntity(thisUser);
			if (allFriends!=null && allFriends.contains(thisUser.getId())) {
				userDao.setFriends(true);
			}
			userDaos.add(userDao);
		}
		Collections.sort(userDaos, (u1, u2) -> {
			if (u1.isFriends() == u2.isFriends()) {
				return u1.getDisplayName().compareTo(u2.getDisplayName());
			}
			return (u1.isFriends() ? -1 : 1);
		});
		return userDaos;
	}

	@Transactional
	public FriendDao updateFriend(UserEntity user, Long friendId, boolean friends) throws UserNotFoundException {
		UserEntity friendUserEntity = userRepo.getOne(friendId);
		if (friendUserEntity==null) {
			throw new UserNotFoundException("Specified user does not exist");
		}
		final FriendEntity friendEntity = friendRepo.findFriend(user.getId(), friendId);
		final FriendDao friendDao = FriendDao.fromEntity(friendUserEntity, friends);

		if (!friends) {
			// delete the row
			if (friendEntity!=null) {
		        friendRepo.delete(friendEntity);
			}
			return friendDao;
		} 

		// check if already selected
		if (friendEntity!=null) {    
	        return friendDao;
		}
		// newly selected so we need to add a row
		final FriendEntity newEntity = new FriendEntity();
		newEntity.setFriendid(friendId);
		newEntity.setUserid(user.getId());
		friendRepo.save(newEntity);
		return friendDao;
	}

	public List<FriendDao> getAllUsersAndFriends(UserEntity user) {
		final List<UserEntity> allUsers = userRepo.findAll();
		final Set<Long> allFriends = friendRepo.findAllByUser(user.getId());
		final List<FriendDao> friendDaos = new ArrayList<>(allUsers.size());
		for (UserEntity thisUser : allUsers) {
			boolean friends = false;
			if (allFriends!=null && allFriends.contains(thisUser.getId())) {
				friends = true;
			}
			friendDaos.add(FriendDao.fromEntity(thisUser, friends));
		}
		return friendDaos;
	}
	
	@Transactional(readOnly = true)
	public TotalDao getShinyStats(UserEntity user) {
		Long total = userRepo.findShinyTotal();
		Long amount = userRepo.findShiny(user.getId());
		return new TotalDao(total, amount);
	}

	@Transactional(readOnly = true)
	public TotalDao getShadowStats(UserEntity user) {
		Long total = userRepo.findShadowTotal();
		Long amount = userRepo.findShadow(user.getId());
		return new TotalDao(total, amount);
	}

	@Transactional(readOnly = true)
	public List<LeaderDao> getShinyLeaderboard(UserEntity user) {
		final List<LeaderEntity> leaderEntities = userRepo.findShinyLeaders();
		final List<LeaderDao> leaders = new ArrayList<>(leaderEntities.size());
		int rank = 1;
		for (LeaderEntity leaderEntity : leaderEntities) {
			LeaderDao leader = new LeaderDao(rank++, leaderEntity.getDisplayName(), leaderEntity.getTotal());
			leaders.add(leader);
		}
		return leaders;
	}
	
	@Transactional(readOnly = true)
	public List<LeaderDao> getShadowLeaderboard(UserEntity user) {
		final List<LeaderEntity> leaderEntities = userRepo.findShadowLeaders();
		final List<LeaderDao> leaders = new ArrayList<>(leaderEntities.size());
		int rank = 1;
		for (LeaderEntity leaderEntity : leaderEntities) {
			LeaderDao leader = new LeaderDao(rank++, leaderEntity.getDisplayName(), leaderEntity.getTotal());
			leaders.add(leader);
		}
		return leaders;
	}
	
	@Transactional(readOnly = true)
	public List<UserDao> getAllUsersWithShinyPokemon(UserEntity user, Long dexId) {
		// Find how many pokemon have this dex id
		final Integer count = pokemonRepo.countAllShinyByDexId(dexId);
		// Find all users
		final List<UserEntity> allUsers = userRepo.findAll();
		final List<UserEntity> allUsersWithout = new ArrayList<>();
		// Find who has got this shiny and how many of them
		final List<UserShinyCountEntity> userCountEntities = userRepo.findAllShinyByDexId(dexId);
		final Map<Long, Integer> userCounts = userCountEntities.stream().collect(
                Collectors.toMap(UserShinyCountEntity::getId, UserShinyCountEntity::getCount));
		if (userCounts != null) {
			for (UserEntity thisUser : allUsers) {
				if (!userCounts.containsKey(thisUser.getId())) {
					allUsersWithout.add(thisUser);
				} else if (userCounts.get(thisUser.getId())!=count) {
					allUsersWithout.add(thisUser);
				}
			}
		} else {
			// Everybody needs them
			allUsersWithout.addAll(allUsers);
		}
		// Build list of User data objects
		final Set<Long> allFriends = friendRepo.findAllByUser(user.getId());
		List<UserDao> userDaos = new ArrayList<>(userCounts.size());
		for (UserEntity thisUser : allUsersWithout) {
			UserDao userDao = UserDao.fromEntity(thisUser);
			if (allFriends!=null && allFriends.contains(thisUser.getId())) {
				userDao.setFriends(true);
			}
			userDaos.add(userDao);
		}
		Collections.sort(userDaos, (u1, u2) -> {
			if (u1.isFriends() == u2.isFriends()) {
				return u1.getDisplayName().compareTo(u2.getDisplayName());
			}
			return (u1.isFriends() ? -1 : 1);
		});
		return userDaos;
	}
	
	@Transactional(readOnly = true)
	public List<UserDao> getAllUsersWithShadowPokemon(UserEntity user, Long pokemonId) {
		final List<UserEntity> users = userRepo.findAllShadowByPokemonId(pokemonId);
		if (users == null) {
			return Collections.emptyList();
		}
		final Set<Long> allFriends = friendRepo.findAllByUser(user.getId());
		List<UserDao> userDaos = new ArrayList<>(users.size());
		for (UserEntity thisUser : users) {
			UserDao userDao = UserDao.fromEntity(thisUser);
			if (allFriends!=null && allFriends.contains(thisUser.getId())) {
				userDao.setFriends(true);
			}
			userDaos.add(userDao);
		}
		Collections.sort(userDaos, (u1, u2) -> {
			if (u1.isFriends() == u2.isFriends()) {
				return u1.getDisplayName().compareTo(u2.getDisplayName());
			}
			return (u1.isFriends() ? -1 : 1);
		});
		return userDaos;
	}


	@Transactional
	public UserDao updateUser(UserEntity user, boolean costumes) throws UserNotFoundException {
		UserEntity myUserEntity = userRepo.getOne(user.getId());
		myUserEntity.setCostumes(costumes);
		UserEntity savedUser = userRepo.save(myUserEntity);
		return UserDao.fromEntity(savedUser);
	}

	@Transactional(readOnly = true)
	public List<UserDao> getAllUsersWithHundoPokemon(UserEntity user, Long pokemonId) {
		final List<UserEntity> users = userRepo.findAllHundoByPokemonId(pokemonId);
		if (users == null) {
			return Collections.emptyList();
		}
		final Set<Long> allFriends = friendRepo.findAllByUser(user.getId());
		List<UserDao> userDaos = new ArrayList<>(users.size());
		for (UserEntity thisUser : users) {
			UserDao userDao = UserDao.fromEntity(thisUser);
			if (allFriends!=null && allFriends.contains(thisUser.getId())) {
				userDao.setFriends(true);
			}
			userDaos.add(userDao);
		}
		Collections.sort(userDaos, (u1, u2) -> {
			if (u1.isFriends() == u2.isFriends()) {
				return u1.getDisplayName().compareTo(u2.getDisplayName());
			}
			return (u1.isFriends() ? -1 : 1);
		});
		return userDaos;
	}

	@Transactional(readOnly = true)
	public TotalDao getHundoStats(UserEntity user) {
		Long total = userRepo.findHundoTotal();
		Long amount = userRepo.findHundo(user.getId());
		Long count = userRepo.findHundoCount(user.getId());
		return new TotalDao(total, amount, count);
	}

	@Transactional(readOnly = true)
	public List<LeaderDao> getHundoLeaderboard(UserEntity user) {
		final List<LeaderEntity> leaderEntities = userRepo.findHundoLeaders();
		final List<LeaderDao> leaders = new ArrayList<>(leaderEntities.size());
		int rank = 1;
		for (LeaderEntity leaderEntity : leaderEntities) {
			LeaderDao leader = new LeaderDao(rank++, leaderEntity.getDisplayName(), leaderEntity.getTotal());
			leaders.add(leader);
		}
		return leaders;
	}

	@Transactional(readOnly = true)
	public List<LeaderDao> getHundoCountboard(UserEntity user) {
		final List<LeaderEntity> leaderEntities = userRepo.findHundoCountLeaders();
		final List<LeaderDao> leaders = new ArrayList<>(leaderEntities.size());
		int rank = 1;
		for (LeaderEntity leaderEntity : leaderEntities) {
			LeaderDao leader = new LeaderDao(rank++, leaderEntity.getDisplayName(), leaderEntity.getTotal());
			leaders.add(leader);
		}
		return leaders;
	}

}

