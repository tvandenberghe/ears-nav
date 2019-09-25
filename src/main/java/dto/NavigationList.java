package dto;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import org.jdom.Element;
import org.jdom.Namespace;

public class NavigationList {
   Vector<Navigation> NavList;
   long max_time = 0L;

   public NavigationList() {
      this.NavList = new Vector();
   }

   public NavigationList(int max_time) {
      this.max_time = (long)max_time;
      this.NavList = new Vector();
   }

   public long getMax_time() {
      return this.max_time;
   }

   public void setMax_time(long max_time) {
      this.max_time = max_time;
   }

   public Vector<Navigation> getList() {
      return this.NavList;
   }

   public void clearTrack() {
      this.NavList.clear();
   }

   public void addNavigationData(Navigation e) throws Exception {
      Date timestamp1 = null;
      Date timestamp2 = null;
      if (this.NavList.isEmpty()) {
         this.NavList.add(e);
      } else {
         if (this.getMax_time() == 0L) {
            this.NavList.add(e);
         } else {
            try {
               SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
               Date parsedDate1 = dateFormat.parse(((Navigation)this.NavList.lastElement()).getTimeStamp());
               Date parsedDate2 = dateFormat.parse(((Navigation)this.NavList.firstElement()).getTimeStamp());
               timestamp1 = new Timestamp(parsedDate1.getTime());
               timestamp2 = new Timestamp(parsedDate2.getTime());
            } catch (Exception var7) {
               var7.printStackTrace();
            }

            long dif = timestamp1.getTime() - timestamp2.getTime();
            if (dif > this.getMax_time() * 1000L) {
               this.NavList.remove(0);
            }

            this.NavList.add(e);
         }

      }
   }

   public String toStringXML() {
      Namespace ns_ssr = Namespace.getNamespace("ewsl", "http://www.eurofleets.eu/ewsl");
      XmlDocument navigation_element = new XmlDocument();
      Element root = new Element("navigation");
      root.setNamespace(ns_ssr);
      navigation_element.addRoot(root);
      int len = this.getList().size();

      for(int i = 0; i < len; ++i) {
         Element detail = new Element("nav");
         detail.setNamespace(ns_ssr);
         Element el1 = new Element("timestamp");
         el1.setNamespace(ns_ssr);
         if (((Navigation)this.getList().get(i)).getTimeStamp() != null) {
            el1.setText(((Navigation)this.getList().get(i)).getTimeStamp());
         }

         detail.addContent(el1);
         Element el2 = new Element("lon");
         el2.setNamespace(ns_ssr);
         if (((Navigation)this.getList().get(i)).getLon() != null) {
            el2.setText(((Navigation)this.getList().get(i)).getLon().toString());
         }

         detail.addContent(el2);
         Element el3 = new Element("lat");
         el3.setNamespace(ns_ssr);
         if (((Navigation)this.getList().get(i)).getLat() != null) {
            el3.setText(((Navigation)this.getList().get(i)).getLat().toString());
         }

         detail.addContent(el3);
         Element el4 = new Element("depth");
         el4.setNamespace(ns_ssr);
         el4.setAttribute("uom", "meters");
         if (((Navigation)this.getList().get(i)).getDepth() != null) {
            el4.setText(((Navigation)this.getList().get(i)).getDepth().toString());
         }

         detail.addContent(el4);
         Element el5 = new Element("cog");
         el5.setNamespace(ns_ssr);
         el5.setAttribute("uom", "meters");
         if (((Navigation)this.getList().get(i)).getCog() != null) {
            el5.setText(((Navigation)this.getList().get(i)).getCog().toString());
         }

         detail.addContent(el5);
         Element el6 = new Element("sog");
         el6.setNamespace(ns_ssr);
         el6.setAttribute("uom", "meters");
         if (((Navigation)this.getList().get(i)).getSog() != null) {
            el6.setText(((Navigation)this.getList().get(i)).getSog().toString());
         }

         detail.addContent(el6);
         Element el7 = new Element("heading");
         el7.setNamespace(ns_ssr);
         if (((Navigation)this.getList().get(i)).getHeading() != null) {
            el7.setText(((Navigation)this.getList().get(i)).getHeading().toString());
         }

         detail.addContent(el7);
         root.addContent(detail);
      }

      return navigation_element.outString();
   }

   public String trackKML() {
      XmlDocument kmlTrack = new XmlDocument();
      Element doc = new Element("Document");
      Element Placemark = new Element("Placemark");
      doc.addContent(Placemark);
      Element name = new Element("name");
      int len = this.getList().size();
      Navigation nav1 = (Navigation)this.getList().get(0);
      Navigation nav2 = (Navigation)this.getList().get(len - 1);
      name.setText("Navigation from " + nav1.getTimeStamp() + " to " + nav2.getTimeStamp());
      Placemark.addContent(name);
      Element description = new Element("description");
      description.setText("Navigation from " + nav1.getTimeStamp() + " to " + nav2.getTimeStamp());
      Placemark.addContent(description);
      Element LineString = new Element("LineString");
      Element coordinates = new Element("coordinates");
      StringBuffer sb = new StringBuffer();

      int i;
      double depth;
      for(i = 0; i < len; ++i) {
         depth = 0.0D;
         if (((Navigation)this.getList().get(i)).getDepth() != null) {
            depth = ((Navigation)this.getList().get(i)).getDepth();
         }

         if (depth > 0.0D) {
            depth *= -1.0D;
         }

         sb.append(((Navigation)this.getList().get(i)).getLon()).append(",").append(((Navigation)this.getList().get(i)).getLat()).append(",").append(depth).append("\r\n");
      }

      coordinates.setText(sb.toString());
      LineString.addContent(coordinates);
      Placemark.addContent(LineString);

      for(i = 0; i < len; ++i) {
         depth = 0.0D;
         if (((Navigation)this.getList().get(i)).getDepth() != null) {
            depth = ((Navigation)this.getList().get(i)).getDepth();
         }

         if (depth > 0.0D) {
            depth *= -1.0D;
         }

         double lon = ((Navigation)this.getList().get(i)).getLon();
         double lat = ((Navigation)this.getList().get(i)).getLat();
         Element plac = new Element("Placemark");
         Element nam = new Element("name");
         nam.setText(((Navigation)this.getList().get(i)).getTimeStamp());
         plac.addContent(nam);
         Element poi = new Element("Point");
         Element co = new Element("coordinates");
         co.setText(lon + "," + lat + "," + depth);
         poi.addContent(co);
         plac.addContent(poi);
         doc.addContent(plac);
      }

      kmlTrack.addRoot(doc);
      return kmlTrack.outString();
   }

   public String toString() {
      return "NavTrack [track=" + this.NavList + ", max_time=" + this.max_time + "]";
   }
}
    