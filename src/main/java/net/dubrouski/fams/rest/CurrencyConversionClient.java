package net.dubrouski.fams.rest;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import net.dubrouski.fams.model.Rates;

@Named
public class CurrencyConversionClient {
	
	private static final String API_URL = "http://api.fixer.io";
	
	@Inject
	Logger logger;
	
	public Rates createClient(String base){
		 Response response = ClientBuilder.newClient()
				 			.target(API_URL)
				 			.path("latest")
				 			.queryParam("base", base)
				 			.request(MediaType.APPLICATION_JSON)
				 			.get();
		 Rates r = response.readEntity(Rates.class);		 
		 return r;
	}
	
	public Rates createClient(){
		Response response = ClientBuilder.newClient()
	 			.target(API_URL)
	 			.path("latest")
	 			.request(MediaType.APPLICATION_JSON)
	 			.get();
		Rates r = response.readEntity(Rates.class);		 
		return r;
	}
}
