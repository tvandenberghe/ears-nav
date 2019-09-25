package dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.jdom.Element;
import org.jdom.Namespace;

public class Navigation {
   private Long id;
   private String navigationId;
   private String timeStamp;
   private Double lon;
   private Double lat;
   private Double depth;
   private Double cog;
   private Double sog;
   private Double heading;
   private Double sow;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getNavigationId() {
      return this.navigationId;
   }

   public void setNavigationId(String navigationId) {
      this.navigationId = navigationId;
   }

   public String getTimeStamp() {
      return this.timeStamp.toString();
   }

   public void setTimeStamp(String timeStamp) {
      this.timeStamp = timeStamp;
   }

   public Double getLon() {
      return this.lon;
   }

   public void setLon(Double lon) {
      this.lon = lon;
   }

   public Double getLat() {
      return this.lat;
   }

   public void setLat(Double lat) {
      this.lat = lat;
   }

   public Double getDepth() {
      return this.depth;
   }

   public void setDepth(Double depth) {
      this.depth = depth;
   }

   public Double getCog() {
      return this.cog;
   }

   public void setCog(Double cog) {
      this.cog = cog;
   }

   public Double getSog() {
      return this.sog;
   }

   public void setSog(Double sog) {
      this.sog = sog;
   }

   public Double getHeading() {
      return this.heading;
   }

   public void setHeading(Double heading) {
      this.heading = heading;
   }

   public Double getSow() {
      return this.sow;
   }

   public void setSow(Double sow) {
      this.sow = sow;
   }

   public String toStringKML() {
      XmlDocument kml = new XmlDocument();
      Element doc = new Element("Document");
      double depth = 0.0D;
      if (this.getDepth() != null) {
         depth = this.getDepth();
      }

      if (depth > 0.0D) {
         depth *= -1.0D;
      }

      double lon = this.getLon();
      double lat = this.getLat();
      Element plac = new Element("Placemark");
      Element nam = new Element("name");
      nam.setText(this.getTimeStamp().toString());
      plac.addContent(nam);
      Element poi = new Element("Point");
      Element co = new Element("coordinates");
      co.setText(lon + "," + lat + "," + depth);
      poi.addContent(co);
      plac.addContent(poi);
      doc.addContent(plac);
      kml.addRoot(doc);
      return kml.outString();
   }

   public String toStringXML() {
      Namespace ns_ssr = Namespace.getNamespace("ewsl", "http://www.eurofleets.eu/");
      XmlDocument navigation_element = new XmlDocument();
      Element root = new Element("navigation");
      root.setNamespace(ns_ssr);
      navigation_element.addRoot(root);
      Element el1 = new Element("timestamp");
      el1.setNamespace(ns_ssr);
      if (this.getTimeStamp() != null) {
         el1.setText(this.getTimeStamp());
      }

      root.addContent(el1);
      Element el2 = new Element("lon");
      el2.setNamespace(ns_ssr);
      if (this.getLon() != null) {
         el2.setText(this.getLon().toString());
      }

      root.addContent(el2);
      Element el3 = new Element("lat");
      el3.setNamespace(ns_ssr);
      if (this.getLat() != null) {
         el3.setText(this.getLat().toString());
      }

      root.addContent(el3);
      Element el4 = new Element("depth");
      el4.setNamespace(ns_ssr);
      el4.setAttribute("uom", "meters");
      if (this.getDepth() != null) {
         el4.setText(this.getDepth().toString());
      }

      root.addContent(el4);
      Element el5 = new Element("cog");
      el5.setNamespace(ns_ssr);
      el5.setAttribute("uom", "meters");
      if (this.getCog() != null) {
         el5.setText(this.getCog().toString());
      }

      root.addContent(el5);
      Element el6 = new Element("sog");
      el6.setNamespace(ns_ssr);
      el6.setAttribute("uom", "meters");
      if (this.getSog() != null) {
         el6.setText(this.getSog().toString());
      }

      root.addContent(el6);
      Element el7 = new Element("heading");
      el7.setNamespace(ns_ssr);
      if (this.getHeading() != null) {
         el7.setText(this.getHeading().toString());
      }

      root.addContent(el7);
      return navigation_element.outString();
   }

   public String toStringSSR() {
      Namespace ns_ssr = Namespace.getNamespace("ssr", "http://www.eurofleets.eu/");
      XmlDocument navigation_element = new XmlDocument();
      Element root = new Element("last_navigation_data");
      Date revision = new Date();
      SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
      String end_str = df2.format(revision);
      root.setAttribute("revision_date", end_str);
      root.setNamespace(ns_ssr);
      navigation_element.addRoot(root);
      Element el1 = new Element("date_time");
      el1.setNamespace(ns_ssr);
      if (this.getTimeStamp() != null) {
         el1.setText(this.getTimeStamp().replace(" ", "T"));
      }

      root.addContent(el1);
      Element el2 = new Element("longitude");
      el2.setNamespace(ns_ssr);
      if (this.getLon() != null) {
         el2.setText(this.getLon().toString());
      }

      root.addContent(el2);
      Element el3 = new Element("latitude");
      el3.setNamespace(ns_ssr);
      if (this.getLat() != null) {
         el3.setText(this.getLat().toString());
      }

      root.addContent(el3);
      Element el4 = new Element("heading");
      el4.setNamespace(ns_ssr);
      if (this.getHeading() != null) {
         el4.setText(this.getHeading().toString());
      }

      root.addContent(el4);
      Element el5 = new Element("velocity");
      el5.setNamespace(ns_ssr);
      el5.setAttribute("uom", "knots");
      if (this.getSow() != null) {
         el5.setText(this.getSow().toString());
      }

      root.addContent(el5);
      Element el6 = new Element("depth");
      el6.setNamespace(ns_ssr);
      el6.setAttribute("uom", "meters");
      if (this.getDepth() != null) {
         el6.setText(this.getDepth().toString());
      }

      root.addContent(el6);
      return navigation_element.outString();
   }

   public String toStringGML() {
      Namespace ns_ssr = Namespace.getNamespace("ssr", "http://www.eurofleets.eu/");
      Namespace ns_gml = Namespace.getNamespace("gml", "http://www.opengis.net/gml");
      XmlDocument navigation_element = new XmlDocument();
      Element root = new Element("last_navigation_data");
      root.setNamespace(ns_ssr);
      root.addNamespaceDeclaration(ns_gml);
      navigation_element.addRoot(root);
      Element el1 = new Element("date_time");
      el1.setNamespace(ns_ssr);
      if (this.getTimeStamp() != null) {
         el1.setText(this.getTimeStamp().replace(" ", "T"));
      }

      root.addContent(el1);
      Element el2 = new Element("longitude");
      el2.setNamespace(ns_ssr);
      if (this.getLon() != null) {
         el2.setText(this.getLon().toString());
      }

      root.addContent(el2);
      Element el3 = new Element("latitude");
      el3.setNamespace(ns_ssr);
      if (this.getLat() != null) {
         el3.setText(this.getLat().toString());
      }

      root.addContent(el3);
      Element el4 = new Element("heading");
      el4.setNamespace(ns_ssr);
      if (this.getHeading() != null) {
         el4.setText(this.getHeading().toString());
      }

      root.addContent(el4);
      Element el5 = new Element("velocity");
      el5.setNamespace(ns_ssr);
      el5.setAttribute("uom", "knots");
      if (this.getSow() != null) {
         el5.setText(this.getSow().toString());
      }

      root.addContent(el5);
      Element el6 = new Element("depth");
      el6.setNamespace(ns_ssr);
      el6.setAttribute("uom", "meters");
      if (this.getDepth() != null) {
         el6.setText(this.getDepth().toString());
      }

      root.addContent(el6);
      return null;
   }

   public String toString24hNav() {
      Namespace ns_ssr = Namespace.getNamespace("ssr", "http://www.eurofleets.eu/");
      XmlDocument nav_element = new XmlDocument();
      Element root = new Element("daily_navigation_track");
      root.setNamespace(ns_ssr);
      Date revision = new Date();
      SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
      String end_str = df2.format(revision);
      root.setAttribute("xlink_href", "./24hMetGml");
      root.setAttribute("revision_date", end_str);
      nav_element.addRoot(root);
      return nav_element.outString();
   }
}