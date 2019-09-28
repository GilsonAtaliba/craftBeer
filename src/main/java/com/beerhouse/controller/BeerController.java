package com.beerhouse.controller;

import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beerhouse.service.BeerBO;
import com.beerhouse.service.BeerDTO;
import com.beerhouse.service.BeerForm;

@RestController
@RequestMapping(value = "/beer")
public class BeerController {
	
	@Autowired
	private BeerBO beerBO;
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> save(@RequestBody @Valid BeerForm beerForm){
			if(beerBO.createBeer(beerForm)==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();		
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<BeerDTO>> getAllBeer(){
	
		final List<BeerDTO> beerDTO = beerBO.getAllBeer();
		if(beerDTO.isEmpty()) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(beerDTO);
	}
	
	@GetMapping(path = "/{id}",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<BeerDTO> getBeer (@PathVariable Integer id){
		final BeerDTO beerDTO = beerBO.getBeer(id);
		if(beerDTO==null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(beerDTO);
	}
	
	@DeleteMapping(path = "/{name}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> delete(@PathVariable String name){
		if(beerBO.deleteBeer(name)!=null)return ResponseEntity.noContent().build(); 
		return ResponseEntity.notFound().build();
		
	}
	
	@PutMapping(path="/{name}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> updateBeerAllParameters(@PathVariable String name, @RequestBody @Valid BeerForm beerForm){
		if(beerBO.updateAllBeer(name, beerForm)==null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok().build();
		
	}
	
	@PatchMapping(path ="/{name}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> updateBeerOneOrMoreParameters(@PathVariable String name, @RequestBody BeerForm beerForm){
		if(beerBO.updateNotAllBeer(name, beerForm)==null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok().build();
	}
	
}
