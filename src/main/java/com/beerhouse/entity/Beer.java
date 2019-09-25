package com.beerhouse.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author gilson
 *
 */

@Entity @Table(name = "Beer", schema="beer")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Beer {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="name", length=30, nullable=false, unique=false)
	private String name;
	
	@Column(name="ingredients", length=500, nullable=false, unique=true)
	private String ingredients;
	
	@Column(name="alcoholContent", length=20, nullable=false, unique=false)
	String alcoholContent;
	
	@Column(name="price", nullable=false, unique=false)
	private float price;
	
	@Column(name="category", length=20, nullable=false, unique=false)
	private String category;
	
	
}
