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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beerhouse.service.BeerBO;
import com.beerhouse.service.BeerDTO;
import com.beerhouse.service.BeerForm;

@RestController
@RequestMapping(value = "/beer", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes =  MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BeerController {
	
	@Autowired
	BeerBO beerBO;
	
	@PostMapping()
	public ResponseEntity<Void> save(@RequestBody @Valid BeerForm beerForm){
			if(beerBO.createBeer(beerForm)==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();		
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping()
	public ResponseEntity<List<BeerDTO>> getAllBeer(){
	
		final List<BeerDTO> beerDTO = beerBO.getAllBeer();
	
		return ResponseEntity.ok(beerDTO);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<BeerDTO> getBeer (@PathVariable Integer id){
		final BeerDTO beerDTO = beerBO.getBeer(id);
		if(beerDTO==null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(beerDTO);
	}
	
	@DeleteMapping
	public ResponseEntity<Void> delete(@RequestParam String name){
		if(beerBO.deleteBeer(name)==null) return ResponseEntity.notFound().build();
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PutMapping()
	public ResponseEntity<Void> updateBeer(@RequestParam String name, @RequestBody @Valid BeerForm beerForm){
		if(beerBO.updateBeer(name, beerForm)==null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok().build();
		
	}
	
	@PatchMapping()
	public ResponseEntity<Void> updatePrice(@RequestParam String name, @RequestParam float price){
		if(beerBO.updatePrice(name, price)==null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok().build();
	}
	
}
