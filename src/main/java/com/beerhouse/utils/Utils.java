/**
 * 
 */
package com.beerhouse.utils;

import org.springframework.stereotype.Service;

import com.beerhouse.entity.Beer;
import com.beerhouse.service.BeerForm;

/**
 * @author gilson
 *
 */
@Service 
public class Utils {

	public static void copyNotNullProperties(BeerForm beerForm, Beer beer) {
		if(beerForm.getAlcoholContent()!=null&&beerForm.getAlcoholContent()=="") beer.setAlcoholContent(beerForm.getAlcoholContent());
		if(beerForm.getCategory()!=null&&beerForm.getCategory()=="") beer.setCategory(beerForm.getCategory());
		if(beerForm.getIngredients()!=null&&beerForm.getIngredients()=="") beer.setIngredients(beerForm.getIngredients());
		if(beerForm.getName()!=null&&beerForm.getName()!="") beer.setName(beerForm.getName());
		if(beerForm.getPrice()>0) beer.setPrice(beerForm.getPrice());
		
	}

	
}
