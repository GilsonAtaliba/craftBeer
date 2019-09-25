/**
 * 
 */
package com.beerhouse.service;

import java.util.List;

import org.springframework.stereotype.Service;


/**
 * @author gilson
 *
 */

@Service
public interface BeerBO{
	BeerDTO createBeer(BeerForm beerForm);
	BeerDTO deleteBeer(String name);
	BeerDTO updateBeer(String name, BeerForm beerForm);
	List<BeerDTO> getAllBeer();
	BeerDTO getBeer(Integer id);
	BeerDTO updatePrice(String name, float price);

}
