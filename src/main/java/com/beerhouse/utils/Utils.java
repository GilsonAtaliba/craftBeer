/**
 * 
 */
package com.beerhouse.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.beerhouse.entity.Beer;
import com.beerhouse.service.BeerForm;

/**
 * @author gilson
 *
 */
@Service
public class Utils {

	public Beer copyNotNullProperties(Beer beer, BeerForm beerForm) {
		if(beerForm.getAlcoholContent()!=null&&beerForm.getAlcoholContent()!="") beer.setAlcoholContent(beerForm.getAlcoholContent());
		if(beerForm.getCategory()!=null&&beerForm.getCategory()!="") beer.setCategory(beerForm.getCategory());
		if(beerForm.getIngredients()!=null&&beerForm.getIngredients()!="") beer.setIngredients(beerForm.getIngredients());
		if(beerForm.getName()!=null&&beerForm.getName()!="") beer.setName(beerForm.getName());
		if(beerForm.getPrice()>0) beer.setPrice(beerForm.getPrice());
		
		return beer;	
	}

	
	public <T, U> T copyProperties (T obj1, U obj2) {
		
		BeanUtils.copyProperties(obj2, obj1);
		
		return obj1;
	}
	
	
}
