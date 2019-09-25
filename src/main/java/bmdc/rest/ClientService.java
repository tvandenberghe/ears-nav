package bmdc.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


@Path("/a")
public class ClientService {
    
    @Inject
    ICityService cityService; //interface au lieu de classe : couplage faible
    
    
	 @Inject
	    World world;
    
    @GET
    @Path("/b")   
    @Produces("application/json")
    public List<City> message() {
        
    	
    	
    	
    //    List<City> cities = cityService.findAll();

      //  return cities;
    	 List<City> cities = new ArrayList<>();

         cities.add(new City(1L, "Bratislava", 432000));
         cities.add(new City(2L, "Budapest", 1759000));
         cities.add(new City(3L, "Prague", 1280000));
         cities.add(new City(4L, "Warsaw", 1748000));
         cities.add(new City(5L, "Los Angeles", 3971000));
         cities.add(new City(6L, "New York", 8550000));
         cities.add(new City(7L, "Edinburgh", 464000));
         cities.add(new City(8L, "Berlin", 3671000));

         return cities;
    }
    
    
    
    @GET   
	@Path("/c")   
	public String getBookcs() 
	{
		return "ccccccccccccccccccccccccccccccc";
		
		
	}
    
    @GET   
	@Path("/d")   
    public String sayHelloWorld() {
        return "Hello " + world.sayWorld();
    }
    
    
    @PostConstruct
    public void logApresConstruction()
    {
    	System.out.println("---------------------------apres création ");
    }
    
}