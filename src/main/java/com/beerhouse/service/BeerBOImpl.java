package com.beerhouse.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beerhouse.entity.Beer;
import com.beerhouse.repository.BeerRepository;
import com.beerhouse.utils.Utils;


@Service
public class BeerBOImpl implements BeerBO{
	@Autowired
	private BeerRepository beerRepository;
	
	@Autowired
	private Utils utils;
	
	@Override
	public BeerDTO createBeer(BeerForm beerForm) {
		Beer beer = new Beer(); 
		beer = utils.copyProperties(beer, beerForm);
		if(beerRepository.findByName(beer.getName())!=null) return null;
		
		beerRepository.save(beer);
		BeerDTO beerDTO = new BeerDTO();
		beerDTO = utils.copyProperties(beerDTO, beer);
		return beerDTO;
	}

	@Override
	public BeerDTO deleteBeer(String name) {
		final Beer beer = beerRepository.findByName(name);
		if(beer==null) return null; 
		BeerDTO beerDTO = new BeerDTO(); 
		beerDTO = utils.copyProperties(beerDTO, beer);
		beerRepository.delete(beer);
		return beerDTO;
	}

	@Override
	public BeerDTO updateAllBeer(String name, BeerForm beerForm) {
		Beer beer = beerRepository.findByName(name);
		if(beer!=null) {
			Integer beerId = beer.getId();
			beer = utils.copyProperties(beer, beerForm);
			beer.setId(beerId);
			beerRepository.save(beer);
			
			BeerDTO beerDTO = new BeerDTO(); 
			beerDTO = utils.copyProperties(beerDTO, beer);
			
			return beerDTO;
		}
		return null;
	}


	@Override
	public List<BeerDTO> getAllBeer() { 
		final List<BeerDTO> beerDTO = new ArrayList<BeerDTO>(); 		
		final List<Beer> beer = beerRepository.findAll();
		for (Beer b : beer) {
			BeerDTO bDTO = new BeerDTO();
			bDTO  =utils.copyProperties(bDTO, b);
			beerDTO.add(bDTO);
		}
		return beerDTO;
	}

	@Override
	public BeerDTO getBeer(Integer id) {
		final Beer beer = beerRepository.findOne(id);
		if(beer == null) return null;
		BeerDTO beerDTO = new BeerDTO(); 
		beerDTO = utils.copyProperties(beerDTO, beer);
		return beerDTO;
	}
	
	@Override
	public BeerDTO updateNotAllBeer(String name, BeerForm beerForm) {
		Beer beer = beerRepository.findByName(name);
		if(beer!= null) {
			BeerDTO beerDTO = new BeerDTO(); 			
			beer = utils.copyNotNullProperties(beer, beerForm);
			beerDTO = utils.copyProperties(beerDTO, beer);
			beerRepository.save(beer);
			return beerDTO;
		}
		return null;
	}
}
