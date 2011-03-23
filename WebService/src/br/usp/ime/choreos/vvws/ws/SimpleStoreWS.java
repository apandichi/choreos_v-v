package br.usp.ime.choreos.vvws.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import br.usp.ime.choreos.vvws.model.CD;

@WebService
public class SimpleStoreWS {
	
	List<CD> cds = MockCDs.CDList;

	@WebMethod
	public String searchByArtist(String artist) {

		StringBuilder result = new StringBuilder();
		
		for(CD cd : cds){
			if(cd.getArtist().contains(artist)){
				result.append(cd.getTitle() + ";");
			}
		}
			
		return result.toString();
	}

	@WebMethod
	public String searchByGenre(String genre) {
		
		StringBuilder result = new StringBuilder();
		
		for(CD cd : cds){
			if(cd.getGenre().contains(genre)){
				result.append(cd.getTitle() + ";");
			}
		}
			
		return result.toString();
	
	}

	@WebMethod
	public String searchByTitle(String title) {
		
		StringBuilder result = new StringBuilder();
		
		for(CD cd : cds){
			if(cd.getTitle().contains(title)){
				result.append(cd.getTitle() + ";");
			}
		}
			
		return result.toString();
	}

	@WebMethod
	public Boolean purchase(String cdTitle, String customerName) {
		return true;
	}
	
}
