package bmdc.rest;


import java.util.HashSet;
import java.util.Set;


import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import webService.NavService;



@ApplicationPath("/")
public class MyApplication extends Application {
	

	 private Set<Object> singletons = new HashSet<Object>();

	 
	    public  MyApplication() {
	        singletons.add(new Library());
	        singletons.add(new ClientService());
	        singletons.add(new NavService());
	        
	      
	    }
	 
	    @Override
	    public Set<Object> getSingletons() {
	        return singletons;
	    }
	    
	    
}
