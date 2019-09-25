package bmdc.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/library")
public class Library {
	
	@GET   
	@Path("/books")   
	public String getBooks() 
	{
		return "yvddan";
		
		
	}
	
	
	
}
	