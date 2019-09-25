package dto;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class ThermosalList {
   Vector<Thermosal> TssList;
   long max_time = 0L;

   public ThermosalList() {
      this.TssList = new Vector();
   }

   public ThermosalList(int max_time) {
      this.max_time = (long)max_time;
      this.TssList = new Vector();
   }

   public long getMax_time() {
      return this.max_time;
   }

   public void setMax_time(long max_time) {
      this.max_time = max_time;
   }

   public Vector<Thermosal> getList() {
      return this.TssList;
   }

   public void clearTrack() {
      this.TssList.clear();
   }

   public void addTssData(Thermosal e) throws Exception {
      Date timestamp1 = null;
      Date timestamp2 = null;
      if (this.TssList.isEmpty()) {
         this.TssList.add(e);
      } else {
         if (this.getMax_time() == 0L) {
            this.TssList.add(e);
         } else {
            try {
               SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
               Date parsedDate1 = dateFormat.parse(((Thermosal)this.TssList.lastElement()).getFecha());
               Date parsedDate2 = dateFormat.parse(((Thermosal)this.TssList.firstElement()).getFecha());
               timestamp1 = new Timestamp(parsedDate1.getTime());
               timestamp2 = new Timestamp(parsedDate2.getTime());
            } catch (Exception var7) {
               var7.printStackTrace();
            }

            long dif = timestamp1.getTime() - timestamp2.getTime();
            if (dif > this.getMax_time() * 1000L) {
               this.TssList.remove(0);
            }

            this.TssList.add(e);
         }

      }
   }

   public String toString() {
      return "NavTrack [track=" + this.TssList.toString() + ", max_time=" + this.max_time + "]";
   }
}
    