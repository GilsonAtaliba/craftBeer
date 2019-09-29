package com.beerhouse;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.beerhouse.service.BeerDTO;
import com.beerhouse.service.BeerForm;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)

public class ApplicationTests {
	
	@Autowired
	protected TestRestTemplate restTemplate;
	
	@Test
	public void saveTest(){
		BeerForm beerForm = new BeerForm("Cauim", "ingrediente1, ingrediente 2", "7%", 20.5f, "Premium");
		
		ResponseEntity<Void> response = restTemplate.postForEntity("/beer", beerForm, null);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		
		ResponseEntity<Void> responseError = restTemplate.postForEntity("/beer", beerForm, null);
		assertEquals(HttpStatus.BAD_REQUEST, responseError.getStatusCode());
	}
	
	
	@Test
	public void getBeerTest(){	
		BeerDTO beerDTO = new BeerDTO("Cau", "ingrediente1, ingrediente2", "10%", 20.f, "Premium");
		ResponseEntity<BeerDTO> response = restTemplate.getForEntity("/beer/1", BeerDTO.class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(beerDTO.hashCode() , response.getBody().hashCode());
		
		ResponseEntity<BeerDTO> responseError = restTemplate.getForEntity("/beer/1000", BeerDTO.class);
		assertEquals(HttpStatus.NOT_FOUND, responseError.getStatusCode());
	}
	
	@Test
	public void getAllBeerTest() {
		ResponseEntity<List<BeerDTO>> response = restTemplate.exchange("/beer", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<BeerDTO>>() {
				});
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(3, response.getBody().size());
	}
	
	@Test
	public void updateBeerAllParametersTest(){
		BeerForm beerForm = new BeerForm("Cauim", "item1, item2", "8%", 30.5f, "Premium");
		
	    HttpEntity<BeerForm> requestEntity = new HttpEntity<>(beerForm);
	    
	    ResponseEntity<Void> response = restTemplate.exchange("/beer/Cauim", HttpMethod.PUT, requestEntity, Void.class );
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
	    
	    ResponseEntity<Void> responseError = restTemplate.exchange("/beer/Error", HttpMethod.PUT, requestEntity, Void.class );
		assertEquals(HttpStatus.NOT_FOUND, responseError.getStatusCode());
		
	}
	
	@Test
	public void testDelete(){
		 	    
		ResponseEntity<Void> response =restTemplate.exchange("/beer/Cau", HttpMethod.DELETE, 
				null, Void.class);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		
		ResponseEntity<Void> responseError =restTemplate.exchange("/beer/Error", HttpMethod.DELETE, 
				null, Void.class);
		assertEquals(HttpStatus.NOT_FOUND, responseError.getStatusCode());
		
		
	}

	

	
}