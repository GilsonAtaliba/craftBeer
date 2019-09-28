package com.beerhouse.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beerhouse.entity.Beer;
import com.beerhouse.repository.BeerRepository;
import com.beerhouse.utils.Utils;


@Service

public class BeerBOImpl implements BeerBO{
	@Autowired
	private BeerRepository beerRepository;
	
	@Override
	public BeerDTO createBeer(BeerForm beerForm) {
		final Beer beer = new Beer(); 
		BeanUtils.copyProperties(beerForm, beer);
		if(beerRepository.findByName(beer.getName())!=null) return null;
		
		beerRepository.save(beer);
		final BeerDTO beerDTO = new BeerDTO(); 
		BeanUtils.copyProperties(beer, beerDTO);
		return beerDTO;
	}

	@Override
	public BeerDTO deleteBeer(String name) {
		final Beer beer = beerRepository.findByName(name);
		if(beer==null) return null; 
		final BeerDTO beerDTO = new BeerDTO(); 
		BeanUtils.copyProperties(beer, beerDTO);
		beerRepository.delete(beer);
		return beerDTO;
	}

	@Override
	public BeerDTO updateAllBeer(String name, BeerForm beerForm) {
		Beer beer = beerRepository.findByName(name);
		if(beer!=null) {
			Integer beerId = beer.getId();
			BeanUtils.copyProperties(beerForm, beer);
			beer.setId(beerId);
			beerRepository.save(beer);
			
			BeerDTO beerDTO = new BeerDTO(); 
			BeanUtils.copyProperties(beer, beerDTO);
			
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
			BeanUtils.copyProperties(b, bDTO);
			beerDTO.add(bDTO);
		}
		return beerDTO;
	}

	@Override
	public BeerDTO getBeer(Integer id) {
		final Beer beer = beerRepository.findOne(id);
		if(beer == null) return null;
		final BeerDTO beerDTO = new BeerDTO(); 
		BeanUtils.copyProperties(beer, beerDTO);
		return beerDTO;
	}
	
	@Override
	public BeerDTO updateNotAllBeer(String name, BeerForm beerForm) {
		final Beer beer = beerRepository.findByName(name);
		if(beer!= null) {
			final BeerDTO beerDTO = new BeerDTO(); 			
			Utils.copyNotNullProperties(beerForm, beer);
			BeanUtils.copyProperties(beer, beerDTO);
			beerRepository.save(beer);
			return beerDTO;
		}
		return null;
	}
}
