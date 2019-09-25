package webService;



import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.google.gson.Gson;


import dto.Cruise;
import dto.Event;
import dto.Navigation;
import dto.NavigationList;
import dto.Thermosal;
import dto.ThermosalList;
import dto.Weather;
import dto.WeatherList;
import model.ProjectManager;

@Path("/")
public class NavService {
	  private static Logger logger = Logger.getLogger(NavService.class.getName());
	@GET   
	@Path("/getServiceUp")   
	@Produces("text/plain")
	public String getServiceUp() 
	{
		  
		logger.log(Level.INFO, "getServiceUp");
		
	

		return "Service is Up";
		
		
	}
	

	
   @GET
   @Path("/getLastNav")
   @Produces({"application/json"})
   public String navigation() {
      String nav = null;
      logger.log(Level.INFO, "getLastNav" );
      try {
         Navigation navegacio = null;
         ProjectManager projectManager = new ProjectManager();
         navegacio = projectManager.GetLastNav();
         Gson gson = new Gson();
         nav = gson.toJson(navegacio);
      } catch (Exception var5) {
    		logger.log(Level.SEVERE, "getLastNav" + var5.getMessage());
        
      }

      return nav;
   }

   @GET
   @Path("/getLastNavKml")
   @Produces({"application/xml"})
   public String navigationKml() {
      String nav = null;

      try {
         Navigation navegacio = null;
         ProjectManager projectManager = new ProjectManager();
         navegacio = projectManager.GetLastNav();
         nav = navegacio.toStringKML();
      } catch (Exception var4) {
    	  logger.log(Level.SEVERE, "getLastNavKml" + var4.getMessage());
       
      }

      return nav;
   }

   @GET
   @Path("/getLastNavXml")
   @Produces({"application/xml"})
   public String navigationXml() {
      String nav = null;

      try {
         Navigation navegacio = null;
         ProjectManager projectManager = new ProjectManager();
         navegacio = projectManager.GetLastNav();
         nav = navegacio.toStringXML();
      } catch (Exception var4) {
    	  logger.log(Level.SEVERE, "getLastNavXml 1 " + var4.getMessage());
      }

      return nav;
   }

   @GET
   @Path("/getLastNavSSR")
   @Produces({"application/xml"})
   public String navigationSSR() {
      String nav = null;

      try {
         Navigation navegacio = null;
         ProjectManager projectManager = new ProjectManager();
         navegacio = projectManager.GetLastNav();
         nav = navegacio.toStringSSR();
      } catch (Exception var4) {
    	  logger.log(Level.SEVERE, "getLastNavSSR" + var4.getMessage());
      }

      return nav;
   }

   @GET
   @Path("/getCruiseInfoSSR")
   @Produces({"application/xml"})
   public String cruiseInfoSSR() {
      String cr = null;

      try {
         Cruise cruise = null;
         ProjectManager projectManager = new ProjectManager();
         cruise = projectManager.getCruiseInfo();
         cr = cruise.toStringSSR();
      } catch (Exception var4) {
    	  logger.log(Level.SEVERE, "getCruiseInfoSSR" + var4.getMessage());
      }

      return cr;
   }

   @GET
   @Path("/getLastMetSSR")
   @Produces({"application/xml"})
   public String WeatherInfoSSR() {
      String wt = null;

      try {
         Weather weather = null;
         ProjectManager projectManager = new ProjectManager();
         weather = projectManager.getWeatherInfo();
         wt = weather.toStringSSR();
         
         
      } catch (Exception var4) {
    	  logger.log(Level.SEVERE, "getLastMetSSR" + var4.getMessage());
      }

      return wt;
   }

   @GET
   @Path("/24hMetJson")
   @Produces({"application/json"})
   public String Weather24hJson() {
      String wt = null;

      try {
         WeatherList weatherList = null;
         ProjectManager projectManager = new ProjectManager();
         weatherList = projectManager.getWeather24h();
         logger.log(Level.INFO, "24hMetJson" + "rakel - tostring: " + weatherList.toString());
         Gson gson = new Gson();
         System.out.println(gson.toJson(weatherList));
         wt = gson.toJson(weatherList);
      } catch (Exception var5) {
    	  logger.log(Level.SEVERE, "24hMetJson" + var5.getMessage());
      }

      return wt;
   }

   @GET
   @Path("/getLast24hMet")
   @Produces({"application/xml"})
   public String Weather24h() {
      String wt = null;

      try {
         Weather weather = null;
         ProjectManager projectManager = new ProjectManager();
         weather = projectManager.getWeatherInfo();
         wt = weather.toString24hMet();
      } catch (Exception var4) {
    	  logger.log(Level.SEVERE, "getLast24hMet" + var4.getMessage());
      }

      return wt;
   }

   @GET
   @Path("/getLast24hTss")
   @Produces({"application/xml"})
   public String Thermosal24h() {
      String tss = null;

      try {
         new Thermosal();
         ProjectManager projectManager = new ProjectManager();
         Thermosal thermosal = projectManager.getThermosalInfo();
         tss = thermosal.toString24hTss();
      } catch (Exception var4) {
    	  logger.log(Level.SEVERE, "getLast24hTss" + var4.getMessage());
      }

      return tss;
   }

   @GET
   @Path("/24hTssJson")
   @Produces({"application/json"})
   public String Thermosal24hJson() {
      String ts = null;

      try {
         ThermosalList tssList = null;
         ProjectManager projectManager = new ProjectManager();
         tssList = projectManager.getTss24h();
         logger.log(Level.INFO, "24hTssJson" + "RAquel - Surt del getTss24h");
         Gson gson = new Gson();
         logger.log(Level.INFO, "24hTssJson" +gson.toJson(tssList));
         ts = gson.toJson(tssList);
      } catch (Exception var5) {
    	  logger.log(Level.SEVERE, "24hTssJson" + var5.getMessage());
      }

      return ts;
   }

   @GET
   @Path("/getLast24hNav")
   @Produces({"application/xml"})
   public String Navigation24h() {
      String nd = null;

      try {
         new Navigation();
         ProjectManager projectManager = new ProjectManager();
         Navigation NavData = projectManager.GetLastNav();
         nd = NavData.toString24hNav();
      } catch (Exception var4) {
    	  logger.log(Level.SEVERE, "getLast24hNav" + var4.getMessage());
      }

      return nd;
   }

   @GET
   @Path("/getLast24hEvt")
   @Produces({"application/xml"})
   public String Event24h() {
      String nd = null;

      try {
         new Event();
         ProjectManager projectManager = new ProjectManager();
        
         Event EvtData = projectManager.GetLastEvt();
         nd = EvtData.toString24hEvt();
      } catch (Exception var4) {
    	  logger.log(Level.SEVERE, "/getLast24hEvt " + var4.getMessage());
      }

      return nd;
   }

   @GET
   @Path("/getLastTssSSR")
   @Produces({"application/xml"})
   public String ThermosalInfoSSR() {
      String ts = null;

      try {
         Thermosal thermosal = null;
         ProjectManager projectManager = new ProjectManager();
         thermosal = projectManager.getThermosalInfo();
         ts = thermosal.toStringSSR();
      } catch (Exception var4) {
    	  logger.log(Level.SEVERE, "getLastTssSSR" + var4.getMessage());
      }

      return ts;
   }

   @GET
   @Path("/getNearestNav")
   @Produces({"application/json"})
   public String GetNearestNav(@QueryParam("date") String date) {
      String nav = null;

      try {
         Navigation NavData = null;
         ProjectManager projectManager = new ProjectManager();
         NavData = projectManager.GetNearestNav(date);
         Gson gson = new Gson();
         logger.log(Level.INFO, "getNearestNav" + gson.toJson(NavData));
         nav = gson.toJson(NavData);
      } catch (Exception var6) {
    	  logger.log(Level.SEVERE, "getNearestNav" + var6.getMessage());
      }

      return nav;
   }

   @GET
   @Path("/getNearestNavXml")
   @Produces({"application/xml"})
   public String GetNearestNavXml(@QueryParam("date") String date) {
      String nav = null;

      try {
         Navigation NavData = null;
         ProjectManager projectManager = new ProjectManager();
         NavData = projectManager.GetNearestNav(date);
         nav = NavData.toStringXML();
      } catch (Exception var5) {
    	  logger.log(Level.SEVERE, "getNearestNavXml" + var5.getMessage());
      }

      return nav;
   }

   @GET
   @Path("/getNearestNavKml")
   @Produces({"application/xml"})
   public String GetNearestNavKml(@QueryParam("date") String date) {
      String nav = null;

      try {
         Navigation NavData = null;
         ProjectManager projectManager = new ProjectManager();
         NavData = projectManager.GetNearestNav(date);
         nav = NavData.toStringKML();
      } catch (Exception var5) {
    	  logger.log(Level.SEVERE, "getNearestNavKml" + var5.getMessage());
      }

      return nav;
   }

   @GET
   @Path("/getNavBtw")
   @Produces({"application/json"})
   public String GetNavBetw(@QueryParam("initDate") String initDate, @QueryParam("endDate") String endDate) {
      String nav = null;

      try {
         NavigationList NavList = null;
         ProjectManager projectManager = new ProjectManager();
         NavList = projectManager.GetNavBetw(initDate, endDate);
         Gson gson = new Gson();
         logger.log(Level.INFO, "getNavBtw" + gson.toJson(NavList));
         nav = gson.toJson(NavList);
      } catch (Exception var7) {
    	  logger.log(Level.SEVERE, "getNavBtw" + var7.getMessage());
      }

      return nav;
   }

   @GET
   @Path("/getNavBtwXml")
   @Produces({"application/xml"})
   public String GetNavBetwXml(@QueryParam("initDate") String initDate, @QueryParam("endDate") String endDate) {
      String nav = null;

      try {
         NavigationList NavList = null;
         ProjectManager projectManager = new ProjectManager();
         NavList = projectManager.GetNavBetw(initDate, endDate);
         nav = NavList.toStringXML();
      } catch (Exception var6) {
    	  logger.log(Level.SEVERE, "getNavBtwXml" + var6.getMessage());
      }

      return nav;
   }

   @GET
   @Path("/getNavBtwKml")
   @Produces({"application/xml"})
   public String GetNavBetwKml(@QueryParam("initDate") String initDate, @QueryParam("endDate") String endDate) {
      String nav = null;

      try {
         NavigationList NavList = null;
         ProjectManager projectManager = new ProjectManager();
         NavList = projectManager.GetNavBetw(initDate, endDate);
         logger.log(Level.INFO, "getNavBtwKml" + NavList.toString());
         nav = NavList.trackKML();
      } catch (Exception var6) {
    	  logger.log(Level.SEVERE, "getNavBtwKml" + var6.getMessage());
      }

      return nav;
   }
}