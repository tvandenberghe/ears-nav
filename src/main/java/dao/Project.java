package dao;


import dto.Cruise;
import dto.Event;
import dto.Navigation;
import dto.Thermosal;
import dto.Weather;
import webService.NavService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Project
{
  public Project() {}
  private static Logger logger = Logger.getLogger(Project.class.getName());
  
  public Navigation GetLastNav(Connection connection,String typeDatabase) throws Exception
  {
    Navigation nav = new Navigation();
    try {
    	
    	
    	
   //   PreparedStatement ps = connection.prepareStatement("SELECT time, lon, lat, cog, sog, heading, depth FROM Navigation ORDER BY time DESC LIMIT 1");
      
      
   	 PreparedStatement ps;
 	if (typeDatabase.equalsIgnoreCase( "oracle"))
 	{
    ps = 
   connection.prepareStatement("SELECT time, lon, lat, cog, sog, heading, depth FROM  (SELECT time, lon, lat, cog, sog, heading, depth FROM Navigation ORDER BY time DESC   ) where rownum = 1");
		 
 	}
 	else {
 		   ps = 
connection.prepareStatement("SELECT time, lon, lat, cog, sog, heading, depth FROM Navigation ORDER BY time DESC LIMIT 1");
 		     
 	} 	
      
      
      
      
      
      ResultSet rs = ps.executeQuery();
      
      while (rs.next()) {
        nav.setTimeStamp(rs.getTimestamp("time").toString());
        nav.setLon(Double.valueOf(rs.getDouble("lon")));
        nav.setLat(Double.valueOf(rs.getDouble("lat")));
        nav.setCog(Double.valueOf(rs.getDouble("cog")));
        nav.setSog(Double.valueOf(rs.getDouble("sog")));
        nav.setHeading(Double.valueOf(rs.getDouble("heading")));
        nav.setDepth(Double.valueOf(rs.getDouble("depth")));
      }
      return nav;
    } catch (Exception e) {
      throw e;
    }
  }
  
  public Navigation GetNearestNav(Connection connection, String time,String typeDatabase) throws Exception {
    Navigation nav = null;
    
    PreparedStatement PSFinal = null;
    String retorn = "ERROR";
    
    String statement = null;
    Long majors = null;
    Long menors = null;
    Date w_time = new Date();
    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    System.out.println("Raquel - Entra a GetNearestNav, data:" + time);
    try
    {
      System.out.println("Entra al try");
      w_time = df1.parse(time);
    } catch (java.text.ParseException e) {
      System.out.println("Raquel - ERRORRR");
      e.printStackTrace();
    }
    
    System.out.println("Raquel - Entra a GetNearestNav2");
    
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
    String str_time = df.format(w_time);
    
    String strSentenciaMENORS = "SELECT ABS(TIME_TO_SEC(TIMEDIFF('" + str_time + "',(SELECT MAX(time) FROM Navigation WHERE time <= '" + str_time + "'))))";
    System.out.println(strSentenciaMENORS);
    
    String strSentenciaMAJORS = "SELECT ABS(TIME_TO_SEC(TIMEDIFF('" + str_time + "',(SELECT MIN(time) FROM Navigation WHERE time >= '" + str_time + "'))))";
    System.out.println(strSentenciaMAJORS);
    System.out.println("Raquel - Entra a GetNearestNav3");
    
    PreparedStatement ps = connection.prepareStatement(strSentenciaMENORS);
    ResultSet aux = ps.executeQuery();
    try
    {
      if ((aux.next()) && (aux != null)) {
        menors = Long.valueOf(aux.getLong(1));
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    
    ps = connection.prepareStatement(strSentenciaMAJORS);
    aux = ps.executeQuery();
    try {
      if ((aux.next()) && (aux != null)) {
        majors = Long.valueOf(aux.getLong(1));
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    
    if (majors.longValue() == 0L) {
      if (menors.longValue() == 0L) {
        retorn = "MAJOR";
      } else {
        retorn = "MENOR";
      }
    } else if (menors.longValue() == 0L) {
      if (majors.longValue() == 0L) {
        retorn = "MENOR";
      } else {
        retorn = "MAJOR";
      }
    } else if (menors.longValue() < majors.longValue()) {
      retorn = "MENOR";
    } else if (majors.longValue() < menors.longValue()) {
      retorn = "MAJOR";
    } else if (majors == menors) {
      retorn = "MENOR";
    } else {
      retorn = "ERROR";
    }
    
    if (retorn == "MENOR") {
    	
    	
      
      	if (typeDatabase.equalsIgnoreCase( "oracle"))
      	{
         
        	 statement = "SELECT time, lon, lat, cog, sog FROM   (SELECT time, lon, lat, cog, sog FROM Navigation WHERE time <= '" + str_time + "' ORDER BY time DESC ) where rownum = 1";
     		 
      	}
      	else {
      		 statement = "SELECT time, lon, lat, cog, sog FROM Navigation WHERE time <= '" + str_time + "' ORDER BY time DESC LIMIT 1";
      		     
      	} 	
    	
    	
    	
   //   statement = "SELECT time, lon, lat, cog, sog FROM Navigation WHERE time <= '" + str_time + "' ORDER BY time DESC LIMIT 1";
      
      PSFinal = connection.prepareStatement(statement);
    }
    else if (retorn == "MAJOR") {
  //    statement = "SELECT time, lon, lat, cog, sog FROM Navigation WHERE time >= '" + str_time + "' ORDER BY time ASC LIMIT 1";
      

    	if (typeDatabase.equalsIgnoreCase( "oracle"))
    	{
       
      	 statement = "SELECT time, lon, lat, cog, sog FROM  (SELECT time, lon, lat, cog, sog FROM Navigation WHERE time >= '" + str_time + "' ORDER BY time ASC ) where rownum = 1";
   		 
    	}
    	else {
    		 statement = "SELECT time, lon, lat, cog, sog FROM Navigation WHERE time >= '" + str_time + "' ORDER BY time ASC LIMIT 1";
    		     
    	} 	
      
      
      
      
      PSFinal = connection.prepareStatement(statement);
    }
    try
    {
      System.out.println(PSFinal);
      aux = PSFinal.executeQuery();
      
      while (aux.next()) {
        nav = new Navigation();
        nav.setTimeStamp(aux.getTimestamp("time").toString());
        nav.setLon(Double.valueOf(aux.getDouble("lon")));
        nav.setLat(Double.valueOf(aux.getDouble("lat")));
        nav.setCog(Double.valueOf(aux.getDouble("cog")));
        nav.setSog(Double.valueOf(aux.getDouble("sog")));
      }
      
      return nav;
    } catch (Exception e) {
      throw e;
    }
  }
  
  public dto.NavigationList GetNavBetw(Connection connection, String initDate, String endDate) throws Exception
  {
    Navigation nav = new Navigation();
    dto.NavigationList navlist = new dto.NavigationList();
    
    Date w_timeStart = new Date();
    Date w_timeEnd = new Date();
    
    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    try
    {
      w_timeStart = df1.parse(initDate);
    } catch (java.text.ParseException e) {
      e.printStackTrace();
    }
    try {
      w_timeEnd = df1.parse(endDate);
    } catch (java.text.ParseException e) {
      e.printStackTrace();
    }
    
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
    String str_startTime = df.format(w_timeStart);
    String str_endTime = df.format(w_timeEnd);
    
    String strSentencia = "SELECT time, lon, lat, cog, sog FROM Navigation WHERE time >= '" + str_startTime + "' AND time <= '" + str_endTime + "' ORDER BY time DESC";
    try {
      PreparedStatement ps = connection.prepareStatement(strSentencia);
      ResultSet rs = ps.executeQuery();
      
      while (rs.next()) {
        nav = new Navigation();
        nav.setTimeStamp(rs.getTimestamp("time").toString());
        nav.setLon(Double.valueOf(rs.getDouble("lon")));
        nav.setLat(Double.valueOf(rs.getDouble("lat")));
        nav.setCog(Double.valueOf(rs.getDouble("cog")));
        nav.setSog(Double.valueOf(rs.getDouble("sog")));
        navlist.addNavigationData(nav);
        System.out.println("Afegir - " + nav.getTimeStamp());
      }
      return navlist;
    } catch (Exception e) {
      throw e;
    }
  }
  
  public Cruise GetCruise(Connection connection,String typeDatabase) throws Exception {
    Cruise cr = new Cruise();
    System.out.println("Raquel - Entra al Project.GetCruise");
    try {
   //   PreparedStatement ps = connection.prepareStatement("SELECT ID, cruiseId, cruiseName, startDate, endDate, platformClass, platformCode, startingHarbor, arrivalHarbor FROM CRUISE ORDER BY startDate DESC LIMIT 1");
      
   	 PreparedStatement ps;
 	if (typeDatabase.equalsIgnoreCase( "oracle"))
 	{
    ps = 
   connection.prepareStatement(""
   		+ "SELECT ID, cruiseId, cruiseName, startDate, endDate, platformClass, platformCode, startingHarbor, arrivalHarbor FROM  (SELECT ID, cruiseId, cruiseName, startDate, endDate, platformClass, platformCode, startingHarbor, arrivalHarbor FROM CRUISE ORDER BY startDate DESC   ) where rownum = 1");
		 
 	}
 	else {
 		   ps = 
connection.prepareStatement("SELECT ID, cruiseId, cruiseName, startDate, endDate, platformClass, platformCode, startingHarbor, arrivalHarbor FROM CRUISE ORDER BY startDate DESC LIMIT 1");
 		     
 	} 	
      
      
      
      
      
      
      System.out.println("Raquel - ps" + ps);
      ResultSet rs = ps.executeQuery();
      
      while (rs.next()) {
        System.out.println("Raquel - Entra al bucle while");
        cr.setId(Long.valueOf(rs.getLong("ID")));
        cr.setCruiseId(rs.getString("cruiseId"));
        cr.setCruiseName(rs.getString("cruiseName"));
        cr.setStartDate(rs.getDate("startDate"));
        cr.setEndDate(rs.getDate("endDate"));
        cr.setPlatformClass(rs.getString("platformClass"));
        cr.setPlatformCode(rs.getString("platformCode"));
        cr.setStartingHarbor(rs.getString("startingHarbor"));
        cr.setArrivalHarbor(rs.getString("arrivalHarbor"));
      }
      return cr;
    } catch (Exception e) {
      throw e;
    }
  }
  
  public Weather GetLastWeather(Connection connection,String typeDatabase) throws Exception {
	  
	  
	  logger.log(Level.INFO, "GetLastWeather" );
    Weather wt = new Weather();
    /*
     *     PreparedStatement ps = 
    		  connection.prepareStatement("
    		  SELECT date_time, wind_speed, wind_gust, wind_direction, air_temperature, humidity, solar_radiation, air_preasure, surface_water_temperature, instrument_time FROM Weather ORDER BY date_time DESC LIMIT 1");
    
     */
    try {
      	 PreparedStatement ps;
        	if (typeDatabase.equalsIgnoreCase( "oracle"))
        	{
           ps = 
          connection.prepareStatement("SELECT date_time, wind_speed, wind_gust, wind_direction, air_temperature, humidity, solar_radiation, air_preasure, surface_water_temperature, instrument_time FROM ( SELECT date_time, wind_speed, wind_gust, wind_direction, air_temperature, humidity, solar_radiation, air_preasure, surface_water_temperature, instrument_time FROM Weather ORDER BY date_time DESC  ) where rownum = 1");
      		 
        	}
        	else {
        		   ps = 
      connection.prepareStatement(" SELECT date_time, wind_speed, wind_gust, wind_direction, air_temperature, humidity, solar_radiation, air_preasure, surface_water_temperature, instrument_time FROM Weather ORDER BY date_time DESC LIMIT 1");
        		     
        	} 	
    	
    	
    
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {

        wt.setFecha(rs.getDate("date_time"));
        wt.setVelocidad_media_viento(Double.valueOf(rs.getDouble("wind_speed")));
        wt.setVelocidad_inst_viento(Double.valueOf(rs.getDouble("wind_gust")));
        wt.setDireccion_viento(Double.valueOf(rs.getDouble("wind_direction")));
        wt.setTemperatura_aire(Double.valueOf(rs.getDouble("air_temperature")));
        wt.setHumedad(Double.valueOf(rs.getDouble("humidity")));
        wt.setRadiacion_solar(Double.valueOf(rs.getDouble("solar_radiation")));
        wt.setPresion_atm(Double.valueOf(rs.getDouble("air_preasure")));
        wt.setTemperatura_agua(Double.valueOf(rs.getDouble("surface_water_temperature")));
        wt.setFecha_instrumento(rs.getDate("instrument_time"));
      }
      
   //   System.out.println("Raquel - surt del resultset");
      return wt;
    } catch (Exception e) {
      throw e;
    }
  }
  
  public Thermosal GetLastThermosal(Connection connection,String typeDatabase) throws Exception {
    Thermosal ts = new Thermosal();
    

    
    try {
   //   PreparedStatement ps = connection.prepareStatement(""
     // 		+ "SELECT date_time, salinity, surface_water_temperature, raw_fluorometry, conductivity, sigmaT, instrument_time FROM Thermosal ORDER BY date_time DESC LIMIT 1");
      
   	 PreparedStatement ps;
   	if (typeDatabase.equalsIgnoreCase( "oracle"))
   	{
      ps = 
     connection.prepareStatement(""
     		+ "SELECT date_time, salinity, surface_water_temperature, raw_fluorometry, conductivity, sigmaT, instrument_time FROM  (SELECT date_time, salinity, surface_water_temperature, raw_fluorometry, conductivity, sigmaT, instrument_time FROM Thermosal ORDER BY date_time DESC ) where rownum = 1");
 		 
   	}
   	else {
   		   ps = 
 connection.prepareStatement("SELECT date_time, salinity, surface_water_temperature, raw_fluorometry, conductivity, sigmaT, instrument_time FROM Thermosal ORDER BY date_time DESC LIMIT 1");
   		     
   	}
      
      System.out.println("Raquel - Project.getLastThermosal. ps: " + ps);
      ResultSet rs = ps.executeQuery();
      
      while (rs.next()) {
        ts.setFecha(rs.getDate("date_time"));
        ts.setSalinidad(Double.valueOf(rs.getDouble("salinity")));
        ts.setTemperatura(Double.valueOf(rs.getDouble("surface_water_temperature")));
        ts.setFluor(Double.valueOf(rs.getDouble("raw_fluorometry")));
        ts.setConductividad(Double.valueOf(rs.getDouble("conductivity")));
        ts.setSigmat(Double.valueOf(rs.getDouble("sigmaT")));
        ts.setFecha_instrumento(rs.getDate("instrument_time"));
      }
      return ts;
    } catch (Exception e) {
      throw e;
    }
  }
  
  public dto.ThermosalList get24hThermosal(Connection connection) throws Exception {
    Thermosal ts = new Thermosal();
    dto.ThermosalList tsl = new dto.ThermosalList();
    try {
      PreparedStatement ps = connection.prepareStatement("SELECT date_time, salinity, surface_water_temperature, raw_fluorometry, conductivity, sigmaT, instrument_time FROM Thermosal ORDER BY date_time DESC");
      System.out.println("Raquel - Project.get24hThermosal. ps: " + ps);
      ResultSet rs = ps.executeQuery();
      
      while (rs.next()) {
        ts.setFecha(rs.getDate("date_time"));
        ts.setSalinidad(Double.valueOf(rs.getDouble("salinity")));
        ts.setTemperatura(Double.valueOf(rs.getDouble("surface_water_temperature")));
        ts.setFluor(Double.valueOf(rs.getDouble("raw_fluorometry")));
        ts.setConductividad(Double.valueOf(rs.getDouble("conductivity")));
        ts.setSigmat(Double.valueOf(rs.getDouble("sigmaT")));
        ts.setFecha_instrumento(rs.getDate("instrument_time"));
        tsl.addTssData(ts);
      }
      return tsl;
    } catch (Exception e) {
      throw e;
    }
  }
  
  public dto.WeatherList GetWeatherBetw(Connection connection, Date start_time, Date end_time) throws Exception
  {
    Weather wt = new Weather();
    dto.WeatherList weatherList = new dto.WeatherList();
    
    Date w_timeStart = new Date();
    Date w_timeEnd = new Date();
    
    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
    String str_startTime = df.format(start_time);
    String str_endTime = df.format(end_time);
    
    String strSentencia = "SELECT date_time, wind_speed, wind_gust, wind_direction, air_temperature, humidity, solar_radiation, air_preasure, surface_water_temperature, instrument_time FROM Weather WHERE date_time >= '" + str_endTime + "' AND date_time <= '" + str_startTime + "' ORDER BY date_time DESC";
    

    System.out.println(strSentencia);
    try {
      PreparedStatement ps = connection.prepareStatement(strSentencia);
      ResultSet rs = ps.executeQuery();
      
      while (rs.next()) {
        wt.setFecha(rs.getDate("date_time"));
        wt.setVelocidad_media_viento(Double.valueOf(rs.getDouble("wind_speed")));
        wt.setVelocidad_inst_viento(Double.valueOf(rs.getDouble("wind_gust")));
        wt.setDireccion_viento(Double.valueOf(rs.getDouble("wind_direction")));
        wt.setTemperatura_aire(Double.valueOf(rs.getDouble("air_temperature")));
        wt.setHumedad(Double.valueOf(rs.getDouble("humidity")));
        wt.setRadiacion_solar(Double.valueOf(rs.getDouble("solar_radiation")));
        wt.setPresion_atm(Double.valueOf(rs.getDouble("air_preasure")));
        wt.setTemperatura_agua(Double.valueOf(rs.getDouble("surface_water_temperature")));
        wt.setFecha_instrumento(rs.getDate("instrument_time"));
        weatherList.addWeatherData(wt);
        System.out.println("Afegir - " + wt.getFecha());
      }
      System.out.println("tostring: " + ((Weather)weatherList.getList().get(0)).getFecha());
      return weatherList;
    } catch (Exception e) {
      throw e;
    }
  }
  
  public Event GetLastEvent(Connection connection,String typeDatabase) throws Exception {
    Event evt = new Event();
    try {
    	 PreparedStatement ps;
    	if (typeDatabase.equalsIgnoreCase( "oracle"))
    	{
       ps = 
      connection.prepareStatement("select timeStamp, actor, subject, actionName, actionProperty, categoryName, toolID, eventId     				  from   				(select timeStamp, actor, subject, actionName, actionProperty, categoryName, toolID, eventId    				   from event    				ORDER BY timeStamp DESC)  	where rownum = 1");
		 
    	}
    	else {
    		   ps = 
 connection.prepareStatement("SELECT timeStamp, actor, subject, actionName, actionProperty, categoryName, toolID, eventId FROM event ORDER BY timeStamp DESC LIMIT 1");
    		     
    	}
      
      
      
        ResultSet rs = ps.executeQuery();
    
      System.out.println("rs: "+rs.getFetchSize() + ps );
      
      while (rs.next()) {
    	 
        evt.setTimeStamp(rs.getDate("timeStamp"));
        evt.setActor(rs.getString("actor"));
        evt.setSubject(rs.getString("subject"));
        evt.setActionName(rs.getString("actionName"));
        evt.setActionProperty(rs.getString("actionProperty"));
        evt.setCategoryName(rs.getString("categoryName"));
        evt.setToolId(Long.valueOf(rs.getLong("toolID")));
        evt.setEventId(rs.getString("eventId"));
        System.out.println(evt.getActor().toString());
      }
      System.out.println("Raquel - surt del getlastevent"+rs.getFetchSize());
      return evt;
    } catch (Exception e) {
      throw e;
    }
  }
}