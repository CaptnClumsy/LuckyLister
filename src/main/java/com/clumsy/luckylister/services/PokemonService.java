package com.clumsy.luckylister.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clumsy.luckylister.data.PokemonDao;
import com.clumsy.luckylister.entities.PokemonEntity;
import com.clumsy.luckylister.entities.UserEntity;
import com.clumsy.luckylister.entities.UserLuckyPokemonEntity;
import com.clumsy.luckylister.exceptions.ObjectNotFoundException;
import com.clumsy.luckylister.repos.PokemonRepo;
import com.clumsy.luckylister.repos.UserLuckyPokemonRepo;

@Service
public class PokemonService {

	private static final String IMAGE_SERVER_URL = "//images.weserv.nl/";
	private static final String GITHUB_ASSET_URL = "raw.githubusercontent.com/ZeChrales/PogoAssets/master/pokemon_icons/";
	private static final int IMAGE_WIDTH=70;

	private final PokemonRepo pokemonRepo;
	private final UserLuckyPokemonRepo luckyPokemonRepo;
	
	@Autowired
	PokemonService(final PokemonRepo pokemonRepo, final UserLuckyPokemonRepo luckyPokemonRepo) {
		this.pokemonRepo = pokemonRepo;
		this.luckyPokemonRepo = luckyPokemonRepo;
	}
	
	private String getPokemonImageUrl(Long dexId, boolean done) {
		String iconId = "";
		if (dexId<10) {
			iconId = "00" + dexId;
		} else if (dexId<100) {
			iconId = "0" + dexId;
		} else {
			iconId = dexId.toString();
		}
		String url = IMAGE_SERVER_URL + "?w="+ IMAGE_WIDTH;
		if (!done) {
			url += "&filt=greyscale";
		}
		url += "&il&url=" + GITHUB_ASSET_URL + 
			"pokemon_icon_" + iconId + "_00.png";
		return url;
	}

	@Transactional(readOnly = true)
	public List<PokemonDao> listPokemon(UserEntity user) {
		final Set<Long> luckyPokemonIds = luckyPokemonRepo.findByUserId(user.getId());
		final List<PokemonEntity> entities = pokemonRepo.findAll();
		List<PokemonDao> daos = new ArrayList<>(entities.size());
		for (PokemonEntity entity : entities) {
			final PokemonDao dao = PokemonDao.fromEntity(entity);
			if (luckyPokemonIds.contains(dao.getId())) {
				dao.setUrl(getPokemonImageUrl(entity.getDexid(), true));
				dao.setDone(true);
			} else {
				dao.setUrl(getPokemonImageUrl(entity.getDexid(), false));
				dao.setDone(false);
			}
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
			// update the image etc
			dao.setUrl(getPokemonImageUrl(pokemonEntity.get().getDexid(), false));
			dao.setDone(false);
			// delete the row
			if (entity!=null) {
		        luckyPokemonRepo.delete(entity);	        
			}
			return dao;
		} 

		// check if already selected
		dao.setUrl(getPokemonImageUrl(pokemonEntity.get().getDexid(), true));
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
}
