/**
 * 
 */
package com.beerhouse.service;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author gilson
 *
 */
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode
public class BeerDTO {
	private String name;
	private String ingredients;
	String alcoholContent;
	private float price;
	private String category;
	
}
