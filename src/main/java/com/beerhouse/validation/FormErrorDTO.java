package com.beerhouse.validation;

import lombok.Getter;

@Getter
public class FormErrorDTO {

	private String label;
	private String error;
	
	public FormErrorDTO(String label, String error) {
		super();
		this.label = label;
		this.error = error;
	}

	
}
