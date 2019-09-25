package com.beerhouse;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import com.beerhouse.Application;
import com.beerhouse.service.BeerBO;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =  WebEnvironment.RANDOM_PORT)
@ComponentScan(basePackageClasses = Application.class)
public class ApplicationTests {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	private HttpHeaders httpHeaders;
	
	@MockBean
	private BeerBO beerBO;
	
	public void RecipeValidationTest() {
		httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
	}
	
	/*@Test
	public void save() throws JsonProcessingException {

		ResponseEntity<Void> response = restTemplate.exchange("/beer", HttpMethod.POST, new HttpEntity<>("{}", httpHeaders) , Void.class);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}*/

}