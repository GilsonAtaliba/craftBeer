package com.beerhouse;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.beerhouse.service.BeerForm;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {
	
	@Autowired
	protected TestRestTemplate restTemplate;
	
	@Test
	public void save(){
		BeerForm beerForm = new BeerForm("Cauim", "ingrediente1, ingrediente 2", "7%", 20.5f, "Premium");
		
		ResponseEntity<Void> response = restTemplate.postForEntity("/beer", beerForm, null);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
}