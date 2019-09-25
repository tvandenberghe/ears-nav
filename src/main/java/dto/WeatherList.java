package dto;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class WeatherList {
   Vector<Weather> WeatherList;
   long max_time = 0L;

   public WeatherList() {
      this.WeatherList = new Vector();
   }

   public WeatherList(int max_time) {
      this.max_time = (long)max_time;
      this.WeatherList = new Vector();
   }

   public long getMax_time() {
      return this.max_time;
   }

   public void setMax_time(long max_time) {
      this.max_time = max_time;
   }

   public Vector<Weather> getList() {
      return this.WeatherList;
   }

   public void clearTrack() {
      this.WeatherList.clear();
   }

   public void addWeatherData(Weather e) throws Exception {
      Date timestamp1 = null;
      Date timestamp2 = null;
      if (this.WeatherList.isEmpty()) {
         System.out.println("Raquel - entra addWeatherData is empty");
         this.WeatherList.add(e);
      } else {
         if (this.getMax_time() == 0L) {
            System.out.println("Raquel - entra addWeatherData getmax_Time == 0");
            this.WeatherList.add(e);
         } else {
            try {
               SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
               Date parsedDate1 = dateFormat.parse(((Weather)this.WeatherList.lastElement()).getFecha());
               Date parsedDate2 = dateFormat.parse(((Weather)this.WeatherList.firstElement()).getFecha());
               timestamp1 = new Timestamp(parsedDate1.getTime());
               timestamp2 = new Timestamp(parsedDate2.getTime());
            } catch (Exception var7) {
               var7.printStackTrace();
            }

            long dif = timestamp1.getTime() - timestamp2.getTime();
            if (dif > this.getMax_time() * 1000L) {
               this.WeatherList.remove(0);
            }

            this.WeatherList.add(e);
         }

      }
   }

   public String toString() {
      return "weather_data [track=" + this.WeatherList + ", max_time=" + this.max_time + "]";
   }
}
    