package com.beerhouse.service;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;

import com.beerhouse.entity.Beer;
import com.beerhouse.repository.BeerRepository;
import com.beerhouse.service.BeerBOImpl;
import com.beerhouse.service.BeerDTO;
import com.beerhouse.service.BeerForm;
import com.beerhouse.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class BeerBOTest {

	@Mock
	private BeerRepository beerRepository;
	
	@Mock
	private Utils utils;
	
	@InjectMocks
	private BeerBOImpl beerBO;
	
	List<Beer> listBeer = Arrays.asList(
			new Beer(1, "beer1", "ingredients1, ingredients2", "7%", 20.0f, "category1"), 
			new Beer(2, "beer2", "ingredients1, ingredients2, ingredients3", "8%", 30.0f, "category2")
			);
	
	Beer beerUpdate = new Beer(1, "beer1", "ingredients1, ingredients2", "8%", 20.0f, "category3");
	
	List<BeerDTO> listBeerDTO = Arrays.asList(
			new BeerDTO("beer1", "ingredients1, ingredients2", "7%", 20.0f, "category1"),
			new BeerDTO("beer2", "ingredients1, ingredients2, ingredients3", "8%", 30.0f, "category2"),
			new BeerDTO("beer1", "ingredients1, ingredients2", "8%", 20.0f, "category3")
			);
			
	
	List<BeerForm> listBeerForm = Arrays.asList(
			new BeerForm("beer1", "ingredients1, ingredients2", "7%", 20.0f, "category1"),
			new BeerForm(null, " ", "8%", 0, "category3")
			);
	
	@Before
	public void setup() {

		when(utils.copyProperties(any(Beer.class), eq(listBeerForm.get(0)))).thenReturn(listBeer.get(0));
		when(utils.copyProperties(any(BeerDTO.class), eq(listBeer.get(0)))).thenReturn(listBeerDTO.get(0));
		when(utils.copyProperties(any(BeerDTO.class), eq(beerUpdate))).thenReturn(listBeerDTO.get(2));
		when(utils.copyNotNullProperties(listBeer.get(0), listBeerForm.get(1))).thenReturn(beerUpdate);
	}
	
	@Test
	public void createTest() {
		BeerDTO beerDTOAtual = beerBO.createBeer(listBeerForm.get(0));
		assertNotNull(beerDTOAtual);
		assertEquals(listBeerDTO.get(0).hashCode(), beerDTOAtual.hashCode());
		
		when(beerRepository.findByName("beer1")).thenReturn(listBeer.get(0));
		BeerDTO beerDTOAtualError = beerBO.createBeer(listBeerForm.get(0));
		assertNull(beerDTOAtualError);
		
	}
	
	@Test
	public void deleteTest() {
		when(beerRepository.findByName("beer1")).thenReturn(listBeer.get(0));
		BeerDTO beerDTOAtual = beerBO.deleteBeer("beer1");
		assertNotNull(beerDTOAtual);
		assertEquals(listBeerDTO.get(0).hashCode(), beerDTOAtual.hashCode());
		
		BeerDTO beerDTOAtualError = beerBO.deleteBeer("error");
		assertNull(beerDTOAtualError);	
	}
	
	@Test
	public void updateAllBeerTest() {
		when(beerRepository.findByName("beer1")).thenReturn(listBeer.get(0));
		BeerDTO beerDTOAtual = beerBO.updateAllBeer("beer1", listBeerForm.get(0));
		assertNotNull(beerDTOAtual);
		assertEquals(listBeerDTO.get(0).hashCode(), beerDTOAtual.hashCode());
		
		BeerDTO beerDTOAtualError = beerBO.updateAllBeer("error", listBeerForm.get(0));
		assertNull(beerDTOAtualError);		
	}
	
	@Test
	public void getAllBeerTest() {
		when(beerRepository.findAll()).thenReturn(listBeer);
		List<BeerDTO> beerDTOAtual = beerBO.getAllBeer();
		assertEquals(2, beerDTOAtual.size());
	}
	
	@Test
	public void getBeerTest() {
		when(beerRepository.findOne(1)).thenReturn(listBeer.get(0));
		BeerDTO beerDTOAtual = beerBO.getBeer(1);
		assertEquals(listBeerDTO.get(0).hashCode(), beerDTOAtual.hashCode());
		
		BeerDTO beerDTOAtualError = beerBO.getBeer(2);
		assertEquals(null, beerDTOAtualError);		
	}
	
	@Test
	public void updateNotAllBeerTest() {
		when(beerRepository.findByName("beer1")).thenReturn(listBeer.get(0));
		BeerDTO beerDTOAtual = beerBO.updateNotAllBeer("beer1", listBeerForm.get(1));
		assertNotNull(beerDTOAtual);
		assertEquals(listBeerDTO.get(2).hashCode(), beerDTOAtual.hashCode());
		
		BeerDTO beerDTOAtualError = beerBO.updateAllBeer("error", listBeerForm.get(1));
		assertNull(beerDTOAtualError);		
	}
	

}
