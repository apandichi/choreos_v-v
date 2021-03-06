package eu.choreos.vvws.ws;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import eu.choreos.vvws.common.CD;
import eu.choreos.vvws.common.Customer;
import eu.choreos.vvws.storews.StoreWS;
import eu.choreos.vvws.storews.StoreWSImpl;



public class StoreTest {

	@Test
	public void testPurchaseShouldntWork(){
		StoreWS ws = new StoreWSImpl();

		assertFalse(ws.purchase(new CD(), new Customer()));
		
	}
	
	@Test
	public void testSearchByArtistWithMock(){
		StoreWSImpl ws = new StoreWSImpl();
		
		List<CD> cds = ws.searchByArtist("Non existant artist");
		assertTrue(cds.isEmpty());
		
		cds = ws.searchByArtist("Floyd");
		assertEquals(cds.size(), 1);
		assertEquals(cds.get(0).getArtist(), "Pink Floyd");
	
		// Case sensitive search
		cds = ws.searchByArtist("floyd");
		assertTrue(cds.isEmpty());
	}
	
	@Test
	public void testSearchByGenreWithMock(){
		StoreWS ws = new StoreWSImpl();
		
		List<String> rockArtists = new ArrayList<String>();
		rockArtists.add("Pink Floyd");
		rockArtists.add("Led Zeppelin");
		rockArtists.add("Bon Jovi");
		rockArtists.add("The Beatles");

		List<CD> cds = ws.searchByGenre("Non existant genre");
		assertTrue(cds.isEmpty());
		
		cds = ws.searchByGenre("Rock");
		assertEquals(cds.size(), 4);
		for(CD cd : cds){
			assertTrue(rockArtists.contains(cd.getArtist()));
		}
	}
	
	
}
