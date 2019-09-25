/**
 * 
 */
package com.beerhouse.service;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author gilson
 *
 */

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class BeerForm {

	@NotEmpty @NotNull @Length(min = 5, max = 30)
	private String name;
	@NotEmpty @NotNull @Length(min = 5, max = 500)
	private String ingredients;
	@NotEmpty @NotNull
	String alcoholContent;
	@NotNull
	private float price;
	@NotEmpty @NotNull @Length(min = 3, max = 20)
	private String category;
}
