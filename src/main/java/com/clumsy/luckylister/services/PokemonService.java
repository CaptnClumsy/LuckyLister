package com.clumsy.luckylister.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clumsy.luckylister.data.PokemonDao;
import com.clumsy.luckylister.data.PokemonSelectListDao;
import com.clumsy.luckylister.entities.PokemonEntity;
import com.clumsy.luckylister.entities.UserEntity;
import com.clumsy.luckylister.entities.UserHundoPokemonEntity;
import com.clumsy.luckylister.entities.UserLuckyPokemonEntity;
import com.clumsy.luckylister.entities.UserNinetyEightPokemonEntity;
import com.clumsy.luckylister.entities.UserShadowPokemonEntity;
import com.clumsy.luckylister.entities.UserShinyPokemonEntity;
import com.clumsy.luckylister.exceptions.ObjectNotFoundException;
import com.clumsy.luckylister.repos.PokemonRepo;
import com.clumsy.luckylister.repos.UserHundoPokemonRepo;
import com.clumsy.luckylister.repos.UserLuckyPokemonRepo;
import com.clumsy.luckylister.repos.UserNinetyEightPokemonRepo;
import com.clumsy.luckylister.repos.UserShadowPokemonRepo;
import com.clumsy.luckylister.repos.UserShinyPokemonRepo;

@Service
public class PokemonService {

	private final PokemonRepo pokemonRepo;
	private final UserLuckyPokemonRepo luckyPokemonRepo;
	private final UserShinyPokemonRepo shinyPokemonRepo;
	private final UserShadowPokemonRepo shadowPokemonRepo;
	private final UserHundoPokemonRepo hundoPokemonRepo;
	private final UserNinetyEightPokemonRepo ninetyeightPokemonRepo;
	
	@Autowired
	PokemonService(final PokemonRepo pokemonRepo, final UserLuckyPokemonRepo luckyPokemonRepo,
		final UserShinyPokemonRepo shinyPokemonRepo, final UserHundoPokemonRepo hundoPokemonRepo,
		final UserShadowPokemonRepo shadowPokemonRepo, final UserNinetyEightPokemonRepo ninetyeightPokemonRepo) {
		this.pokemonRepo = pokemonRepo;
		this.luckyPokemonRepo = luckyPokemonRepo;
		this.shinyPokemonRepo = shinyPokemonRepo;
		this.hundoPokemonRepo = hundoPokemonRepo;
		this.shadowPokemonRepo = shadowPokemonRepo;
		this.ninetyeightPokemonRepo = ninetyeightPokemonRepo;
	}

	@Transactional(readOnly = true)
	public List<PokemonDao> listPokemon(UserEntity user) {
		final Set<Long> luckyPokemonIds = luckyPokemonRepo.findByUserId(user.getId());
		final List<PokemonEntity> entities = pokemonRepo.findAll();
		List<PokemonDao> daos = new ArrayList<>(entities.size());
		for (PokemonEntity entity : entities) {
			final PokemonDao dao = PokemonDao.fromEntity(entity);
			if (luckyPokemonIds.contains(dao.getId())) {
				dao.setDone(true);
			} else {
				dao.setDone(false);
			}
			daos.add(dao);
		}
		return daos;
	}

	@Transactional(readOnly = true)
	public List<PokemonDao> listPokemonForUser(UserEntity user) {
		final List<PokemonEntity> entities = pokemonRepo.findAllForUser(user.getId());
		List<PokemonDao> daos = new ArrayList<>(entities.size());
		for (PokemonEntity entity : entities) {
			final PokemonDao dao = PokemonDao.fromEntity(entity);
			dao.setDone(false);
			daos.add(dao);
		}
		return daos;
	}

	@Transactional
	public PokemonDao updatePokemon(UserEntity user, Long pokemonId, boolean selected) throws ObjectNotFoundException {
		// find if user already selected this pokemon and load the details for it		
		final UserLuckyPokemonEntity entity = luckyPokemonRepo.findByUserIdAndPokemonId(user.getId(), pokemonId);
		final Optional<PokemonEntity> pokemonEntity = pokemonRepo.findById(pokemonId);
		if (!pokemonEntity.isPresent()) {
			throw new ObjectNotFoundException("Unable to find pokemon");
		}
		final PokemonDao dao = PokemonDao.fromEntity(pokemonEntity.get());
		
		if (!selected) {
			dao.setDone(false);
			// delete the row
			if (entity!=null) {
		        luckyPokemonRepo.delete(entity);
			}
			return dao;
		} 

		// check if already selected
		dao.setDone(true);
		if (entity!=null) {    
	        return dao;
		}
		// newly selected so we need to add a row
		final UserLuckyPokemonEntity newEntity = new UserLuckyPokemonEntity();
		newEntity.setPokemonid(pokemonId);
		newEntity.setUserid(user.getId());
		luckyPokemonRepo.save(newEntity);
		return dao;
	}

	@Transactional(readOnly = true)
	public List<PokemonSelectListDao> listAllPokemon() throws ObjectNotFoundException {
		final List<PokemonEntity> entities = pokemonRepo.findAll();
		if (entities==null) {
			throw new ObjectNotFoundException("Unable to find pokemon");
		}
		List<PokemonSelectListDao> daos = new ArrayList<>(entities.size());
		for (PokemonEntity entity : entities) {
			final PokemonSelectListDao dao = PokemonSelectListDao.fromPokemonEntity(entity);
			daos.add(dao);
		}
		return daos;
	}
	
	@Transactional(readOnly = true)
	public List<PokemonSelectListDao> listAllShinyPokemon() throws ObjectNotFoundException {
		final List<PokemonEntity> entities = pokemonRepo.findAllShiny();
		if (entities==null) {
			throw new ObjectNotFoundException("Unable to find pokemon");
		}
		List<PokemonSelectListDao> daos = new ArrayList<>(entities.size());
		for (PokemonEntity entity : entities) {
			final PokemonSelectListDao dao = PokemonSelectListDao.fromPokemonEntity(entity);
			daos.add(dao);
		}
		return daos;
	}

	@Transactional(readOnly = true)
	public List<PokemonSelectListDao> listAllShadowPokemon() throws ObjectNotFoundException {
		final List<PokemonEntity> entities = pokemonRepo.findAllShadow();
		if (entities==null) {
			throw new ObjectNotFoundException("Unable to find pokemon");
		}
		List<PokemonSelectListDao> daos = new ArrayList<>(entities.size());
		for (PokemonEntity entity : entities) {
			final PokemonSelectListDao dao = PokemonSelectListDao.fromPokemonEntity(entity);
			daos.add(dao);
		}
		return daos;
	}
	
	@Transactional(readOnly = true)
	public List<PokemonSelectListDao> listAllHundoPokemon() throws ObjectNotFoundException {
		final List<PokemonEntity> entities = pokemonRepo.findAllHundos();
		if (entities==null) {
			throw new ObjectNotFoundException("Unable to find pokemon");
		}
		List<PokemonSelectListDao> daos = new ArrayList<>(entities.size());
		for (PokemonEntity entity : entities) {
			final PokemonSelectListDao dao = PokemonSelectListDao.fromPokemonEntity(entity);
			daos.add(dao);
		}
		return daos;
	}
	
	@Transactional(readOnly = true)
	public List<PokemonDao> listShinyPokemon(UserEntity user) {
		final Set<Long> shinyPokemonIds = shinyPokemonRepo.findByUserId(user.getId());
		final List<PokemonEntity> entities = pokemonRepo.findAllShiny();
		List<PokemonDao> daos = new ArrayList<>(entities.size());
		for (PokemonEntity entity : entities) {
			final PokemonDao dao = PokemonDao.fromEntity(entity);
			if (shinyPokemonIds.contains(dao.getId())) {
				dao.setDone(true);
			} else {
				dao.setDone(false);
			}
			daos.add(dao);
		}
		return daos;
	}
	
	@Transactional(readOnly = true)
	public List<PokemonDao> listShadowPokemon(UserEntity user) {
		final Set<Long> shadowPokemonIds = shadowPokemonRepo.findByUserId(user.getId());
		final List<PokemonEntity> entities = pokemonRepo.findAllShadow();
		List<PokemonDao> daos = new ArrayList<>(entities.size());
		for (PokemonEntity entity : entities) {
			final PokemonDao dao = PokemonDao.fromEntity(entity);
			if (shadowPokemonIds.contains(dao.getId())) {
				dao.setDone(true);
			} else {
				dao.setDone(false);
			}
			daos.add(dao);
		}
		return daos;
	}
	
	@Transactional
	public PokemonDao updateShinyPokemon(UserEntity user, Long pokemonId, boolean selected) throws ObjectNotFoundException {
		// find if user already selected this pokemon and load the details for it		
		final UserShinyPokemonEntity entity = shinyPokemonRepo.findByUserIdAndPokemonId(user.getId(), pokemonId);
		final Optional<PokemonEntity> pokemonEntity = pokemonRepo.findById(pokemonId);
		if (!pokemonEntity.isPresent()) {
			throw new ObjectNotFoundException("Unable to find pokemon");
		}
		final PokemonDao dao = PokemonDao.fromEntity(pokemonEntity.get());
		
		if (!selected) {
			dao.setDone(false);
			// delete the row
			if (entity!=null) {
		        shinyPokemonRepo.delete(entity);
			}
			return dao;
		} 

		// check if already selected
		dao.setDone(true);
		if (entity!=null) {    
	        return dao;
		}
		// newly selected so we need to add a row
		final UserShinyPokemonEntity newEntity = new UserShinyPokemonEntity();
		newEntity.setPokemonid(pokemonId);
		newEntity.setUserid(user.getId());
		shinyPokemonRepo.save(newEntity);
		return dao;
	}
	
	@Transactional
	public PokemonDao updateShadowPokemon(UserEntity user, Long pokemonId, boolean selected) throws ObjectNotFoundException {
		// find if user already selected this pokemon and load the details for it		
		final UserShadowPokemonEntity entity = shadowPokemonRepo.findByUserIdAndPokemonId(user.getId(), pokemonId);
		final Optional<PokemonEntity> pokemonEntity = pokemonRepo.findById(pokemonId);
		if (!pokemonEntity.isPresent()) {
			throw new ObjectNotFoundException("Unable to find pokemon");
		}
		final PokemonDao dao = PokemonDao.fromEntity(pokemonEntity.get());
		
		if (!selected) {
			dao.setDone(false);
			// delete the row
			if (entity!=null) {
		        shadowPokemonRepo.delete(entity);
			}
			return dao;
		} 

		// check if already selected
		dao.setDone(true);
		if (entity!=null) {    
	        return dao;
		}
		// newly selected so we need to add a row
		final UserShadowPokemonEntity newEntity = new UserShadowPokemonEntity();
		newEntity.setPokemonid(pokemonId);
		newEntity.setUserid(user.getId());
		shadowPokemonRepo.save(newEntity);
		return dao;
	}
	
	@Transactional(readOnly = true)
	public List<PokemonDao> listShinyPokemonForUser(UserEntity user) {
		final List<PokemonEntity> entities = pokemonRepo.findAllShinyForUser(user.getId());
		List<PokemonDao> daos = new ArrayList<>(entities.size());
		for (PokemonEntity entity : entities) {
			final PokemonDao dao = PokemonDao.fromEntity(entity);
			dao.setDone(false);
			daos.add(dao);
		}
		return daos;
	}
	
	@Transactional(readOnly = true)
	public List<PokemonDao> listShadowPokemonForUser(UserEntity user) {
		final List<PokemonEntity> entities = pokemonRepo.findAllShadowForUser(user.getId());
		List<PokemonDao> daos = new ArrayList<>(entities.size());
		for (PokemonEntity entity : entities) {
			final PokemonDao dao = PokemonDao.fromEntity(entity);
			dao.setDone(false);
			daos.add(dao);
		}
		return daos;
	}
	
	@Transactional(readOnly = true)
	public PokemonDao getPokemon(Long pokemonId) throws ObjectNotFoundException {
		final Optional<PokemonEntity> entity = pokemonRepo.findById(pokemonId);
		if (!entity.isPresent()) {
			throw new ObjectNotFoundException("Unable to find Pokemon");
		}
		final PokemonDao dao = PokemonDao.fromEntity(entity.get());
		dao.setDone(false);
		return dao;
	}

	@Transactional(readOnly = true)
	public List<PokemonDao> listHundoPokemon(UserEntity user) {
		final List<UserHundoPokemonEntity> hundos = hundoPokemonRepo.findByUserId(user.getId());
		final Map<Long, UserHundoPokemonEntity> hundoPokemon = 
			hundos.stream().collect(Collectors.toMap(UserHundoPokemonEntity::getPokemonid, item -> item));
		final List<PokemonEntity> entities = pokemonRepo.findAllHundos();
		List<PokemonDao> daos = new ArrayList<>(entities.size());
		for (PokemonEntity entity : entities) {
			final PokemonDao dao = PokemonDao.fromEntity(entity);
			final UserHundoPokemonEntity hundoEntry = hundoPokemon.get(dao.getId());
			if (hundoEntry != null) {
				dao.setDone(true);
				dao.setTotal(hundoEntry.getTotal());
			} else {
				dao.setDone(false);
				dao.setTotal(0L);
			}
			daos.add(dao);
		}
		return daos;
	}

	@Transactional
	public PokemonDao updateHundoPokemon(UserEntity user, Long pokemonId, boolean selected, Long total) throws ObjectNotFoundException {
		// find if user already selected this pokemon and load the details for it		
		final UserHundoPokemonEntity entity = hundoPokemonRepo.findByUserIdAndPokemonId(user.getId(), pokemonId);
		final Optional<PokemonEntity> pokemonEntity = pokemonRepo.findById(pokemonId);
		if (!pokemonEntity.isPresent()) {
			throw new ObjectNotFoundException("Unable to find pokemon");
		}
		final PokemonDao dao = PokemonDao.fromEntity(pokemonEntity.get());
		
		if (!selected) {
			dao.setDone(false);
			dao.setTotal(0L);
			// delete the row
			if (entity!=null) {
		        hundoPokemonRepo.delete(entity);
			}
			return dao;
		} 

		// check if already selected
		dao.setDone(true);
		if (entity!=null) {    
	        entity.setTotal(total);
	        dao.setTotal(total);
	        hundoPokemonRepo.save(entity);
	        return dao;
		}
		// newly selected so we need to add a row
		final UserHundoPokemonEntity newEntity = new UserHundoPokemonEntity();
		newEntity.setPokemonid(pokemonId);
		newEntity.setUserid(user.getId());
		newEntity.setTotal(1L);
		hundoPokemonRepo.save(newEntity);
		return dao;
	}

	@Transactional(readOnly = true)
	public List<PokemonDao> listNinetyEightPokemon(UserEntity user) {
		final List<UserNinetyEightPokemonEntity> hundos = ninetyeightPokemonRepo.findByUserId(user.getId());
		final Map<Long, UserNinetyEightPokemonEntity> hundoPokemon = 
			hundos.stream().collect(Collectors.toMap(UserNinetyEightPokemonEntity::getPokemonid, item -> item));
		final List<PokemonEntity> entities = pokemonRepo.findAllHundos();
		List<PokemonDao> daos = new ArrayList<>(entities.size());
		for (PokemonEntity entity : entities) {
			final PokemonDao dao = PokemonDao.fromEntity(entity);
			final UserNinetyEightPokemonEntity hundoEntry = hundoPokemon.get(dao.getId());
			if (hundoEntry != null) {
				dao.setDone(true);
				dao.setTotal(hundoEntry.getTotal());
			} else {
				dao.setDone(false);
				dao.setTotal(0L);
			}
			daos.add(dao);
		}
		return daos;
	}

	@Transactional
	public PokemonDao updateNinetyEightPokemon(UserEntity user, Long pokemonId, boolean selected, Long total) throws ObjectNotFoundException {
		// find if user already selected this pokemon and load the details for it		
		final UserNinetyEightPokemonEntity entity = ninetyeightPokemonRepo.findByUserIdAndPokemonId(user.getId(), pokemonId);
		final Optional<PokemonEntity> pokemonEntity = pokemonRepo.findById(pokemonId);
		if (!pokemonEntity.isPresent()) {
			throw new ObjectNotFoundException("Unable to find pokemon");
		}
		final PokemonDao dao = PokemonDao.fromEntity(pokemonEntity.get());
		
		if (!selected) {
			dao.setDone(false);
			dao.setTotal(0L);
			// delete the row
			if (entity!=null) {
				ninetyeightPokemonRepo.delete(entity);
			}
			return dao;
		} 

		// check if already selected
		dao.setDone(true);
		if (entity!=null) {    
	        entity.setTotal(total);
	        dao.setTotal(total);
	        ninetyeightPokemonRepo.save(entity);
	        return dao;
		}
		// newly selected so we need to add a row
		final UserNinetyEightPokemonEntity newEntity = new UserNinetyEightPokemonEntity();
		newEntity.setPokemonid(pokemonId);
		newEntity.setUserid(user.getId());
		newEntity.setTotal(1L);
		ninetyeightPokemonRepo.save(newEntity);
		return dao;
	}
}
