package com.beerhouse.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beerhouse.entity.Beer;
import com.beerhouse.repository.BeerRepository;


@Service
public class BeerBOimpl implements BeerBO{
	@Autowired
	BeerRepository beerRepository;
	
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
		beerRepository.delete(beer);
		BeanUtils.copyProperties(beer, beerDTO);
		return beerDTO;
	}

	@Override
	public BeerDTO updateBeer(String name, BeerForm beerForm) {
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
		BeanUtils.copyProperties(beer, beerDTO);
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
	public BeerDTO updatePrice(String name, float price) {
		final Beer beer = beerRepository.findByName(name);
		if(beer!= null) {
			final BeerDTO beerDTO = new BeerDTO(); 
			BeanUtils.copyProperties(beer, beerDTO);
			beer.setPrice(price);
			beerRepository.save(beer);
			return beerDTO;
		}
		return null;
	}
}
