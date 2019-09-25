package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Database {
	
	  private static Logger logger = Logger.getLogger(Database.class.getName());
	  
	
  public Database() {
	  
	
	  
	  
  }
  
  public java.sql.Connection Get_Connection() throws Exception {
	
	  
	  
	  File configDir = new File(System.getProperty("catalina.base"), "ears");
	  File configFile = new File(configDir, "application.properties");
	  InputStream stream = new FileInputStream(configFile);
	  Properties props = new Properties();
		  
	  props.load(stream);
	
     
		  
	  Properties properties = new Properties();
	  properties.setProperty("user",props.getProperty("user"));
	  properties.setProperty("password", props.getProperty("password"));
	  properties.setProperty("useSSL",props.getProperty("useSSL"));
	  properties.setProperty("autoReconnect", props.getProperty("autoReconnect"));
	  properties.setProperty("driverClassName", props.getProperty("driverClassName"));
	 
	  
	  /*
	   * ys
		String connectionURL = "jdbc:mysql://localhost:3306/casino";//jdbc:mysql://belbmdc.odnature.be:3306/casino
 */

	  
	  
    try { 
    	
   Class.forName(props.getProperty("driverClassName")).newInstance();
   Set<String> keys = properties.stringPropertyNames();
   for (String key : keys) {
     System.out.println(key + " : " + properties.getProperty(key));
   }
   
   return java.sql.DriverManager.getConnection(props.getProperty("connectionURL"), properties);
   //return java.sql.DriverManager.getConnection(props.getProperty("connectionURL"),props.getProperty("user") , props.getProperty("password"));

    } catch (ClassNotFoundException e) {
    
    	logger.log(Level.SEVERE, "An exception occured. No se puede cargar el driver mysql/oracle "+ e.getMessage());
    	
     
      throw e;
    } catch (java.sql.SQLException e) {
    	logger.log(Level.SEVERE, "An exception occured.Raquel - connexion error 1 "+ e.getMessage() +e.getSQLState() +e.getErrorCode());
    
     
      throw e;
    } catch (Exception e) {
    	logger.log(Level.SEVERE, "An exception occured.Raquel - connexion error 2"+ e.getMessage());
    	
         throw e;
    }
  }
}