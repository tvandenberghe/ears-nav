package model;


import dao.Database;
import dao.Project;
import dto.Cruise;
import dto.Event;
import dto.Navigation;
import dto.NavigationList;
import dto.Thermosal;
import dto.ThermosalList;
import dto.Weather;
import dto.WeatherList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProjectManager {
	
	  private static Logger logger = Logger.getLogger(ProjectManager.class.getName());
	
   public Navigation GetLastNav() throws Exception {
      Navigation nav = null;

      
      Statement stmt = null;
      ResultSet rs = null;
     
      
      try {
         Database database = new Database();
         Connection connection = database.Get_Connection();
         
         
        
   /*YS test if connection ok */
             stmt = connection.createStatement();
             rs = stmt.executeQuery("SELECT * FROM casino.event"); //error de syntaxe ; ??
             ArrayList<String> arrayList = new ArrayList<String>(); 
             int i = 1;
             while (rs.next()) {              
            	 logger.log(Level.INFO, "GetLastNav "+ rs.getString("actor"));
     		
            	 
            	}

         
         Project project = new Project();
         
         
         
         if (connection.getClass().getCanonicalName().toLowerCase().contains("oracle")) {
        	 logger.log(Level.INFO, "GetLastNav "+"oracle");
      		
        	   nav = project.GetLastNav(connection,"oracle");
       	 }
 			else 
 			{
 				 logger.log(Level.INFO, "GetLastNav "+"mysql");
 				 nav = project.GetLastNav(connection,"mysql");
 			
 		}
         
         
      
         return nav;
      } catch (Exception var5) {
         throw var5;
      }
   }

   public Navigation GetNearestNav(String time) throws Exception {
      Navigation nav = null;

      try {
         Database database = new Database();
         Connection connection = database.Get_Connection();
         Project project = new Project();
         
         if (connection.getClass().getCanonicalName().toLowerCase().contains("oracle")) {
      		 
        	   nav = project.GetNearestNav(connection, time,"oracle");
     	 }
			else 
			{
				   nav = project.GetNearestNav(connection, time,"mysql");
			
		}
       
         
         
      
         return nav;
      } catch (Exception var6) {
         throw var6;
      }
   }

   public NavigationList GetNavBetw(String initDate, String endDate) throws Exception {
      NavigationList navList = null;

      try {
         Database database = new Database();
         Connection connection = database.Get_Connection();
         Project project = new Project();
         navList = project.GetNavBetw(connection, initDate, endDate);
         return navList;
      } catch (Exception var7) {
         throw var7;
      }
   }

   public Cruise getCruiseInfo() throws Exception {
      Cruise cruise = null;
      System.out.println("Raquel - Entra al ProjectManager.getCruiseInfo");

      try {
         Database database = new Database();
         Connection connection = database.Get_Connection();
         Project project = new Project();
         System.out.println("Raquel - Abans de cridar GetCruise");
         
         
         if (connection.getClass().getCanonicalName().toLowerCase().contains("oracle")) {
      		 
        	  cruise = project.GetCruise(connection,"oracle");
     	 }
			else 
			{
				  cruise = project.GetCruise(connection,"mysql");
			
		}
       
         
         
       
         System.out.println("Raquel - Després de cridar GetCruise");
         return cruise;
      } catch (Exception var5) {
         throw var5;
      }
   }

   public Weather getWeatherInfo() throws Exception {
      Weather weather = null;

      try {
         Database database = new Database();
         Connection connection = database.Get_Connection();
         Project project = new Project();
         
         if (connection.getClass().getCanonicalName().toLowerCase().contains("oracle")) {
      		 
        	  weather = project.GetLastWeather(connection,"oracle");
    	 }
			else 
			{
				  weather = project.GetLastWeather(connection,"mysql");
			
		}
         
       
         return weather;
      } catch (Exception var5) {
         throw var5;
      }
   }

   public Thermosal getThermosalInfo() throws Exception {
      Thermosal thermosal = null;

      try {
         Database database = new Database();
         Connection connection = database.Get_Connection();
         Project project = new Project();
         
         
         if (connection.getClass().getCanonicalName().toLowerCase().contains("oracle")) {
      		 
        	  thermosal = project.GetLastThermosal(connection,"oracle");
    	 }
			else 
			{
				  thermosal = project.GetLastThermosal(connection,"mysql");
			
		}
         
         
       
         return thermosal;
      } catch (Exception var5) {
         throw var5;
      }
   }

   public WeatherList getWeather24h() throws Exception {
      WeatherList wthList = null;

      try {
         Database database = new Database();
         Connection connection = database.Get_Connection();
         Project project = new Project();
         Calendar calendar = Calendar.getInstance();
         Date start_time = calendar.getTime();
         calendar.add(5, -1);
         Date end_time = calendar.getTime();
         wthList = project.GetWeatherBetw(connection, start_time, end_time);
         System.out.println("Raquel - projectManager - torna: " + wthList.toString());
         return wthList;
      } catch (Exception var8) {
         throw var8;
      }
   }

   public Event GetLastEvt()  {
      Event event = null;
      
      try {
     	 Database database = new Database();
     	 
          Connection connection = database.Get_Connection();
    
         Project project = new Project();
      	 if (connection.getClass().getCanonicalName().toLowerCase().contains("oracle")) {
      		 
      		event = project.GetLastEvent(connection,"oracle");
      	 }
			else 
			{
				event = project.GetLastEvent(connection,"mysql");
			
		}
 
        
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(Level.SEVERE, "GetLastEvt"+ e.getMessage());
		}
		return event;
 
    /*
         Database database = new Database();
         Connection connection = database.Get_Connection();
         Project project = new Project();
         event = project.GetLastEvent(connection);
         return event;
      } catch (Exception var5) {
     
     	
     	  throw var5;
      }
      */
   }

   public ThermosalList getTss24h() throws Exception {
      ThermosalList thermosalList = null;

      try {
         Database database = new Database();
         Connection connection = database.Get_Connection();
         Project project = new Project();
         System.out.println("Raquel - just abans de getLasThermosal");
         thermosalList = project.get24hThermosal(connection);
         System.out.println("Raquel - just després de getLasThermosal");
         System.out.println("Thermosal - " + ((Thermosal)thermosalList.getList().get(0)).getFecha());
         return null;
      } catch (Exception var5) {
         throw var5;
      }
   }
}