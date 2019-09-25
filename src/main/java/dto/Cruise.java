package dto;


import java.text.SimpleDateFormat;
import java.util.Date;
import org.jdom.Element;
import org.jdom.Namespace;

public class Cruise {
   private Long id;
   private String cruiseId;
   private String cruiseName;
   private Date startDate;
   private Date endDate;
   private String platformClass;
   private String platformCode;
   private String startingHarbor;
   private String arrivalHarbor;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getCruiseId() {
      return this.cruiseId;
   }

   public void setCruiseId(String cruiseId) {
      this.cruiseId = cruiseId;
   }

   public String getCruiseName() {
      return this.cruiseName;
   }

   public void setCruiseName(String cruiseName) {
      this.cruiseName = cruiseName;
   }

   public String getStartDate() {
      return this.startDate.toString();
   }

   public void setStartDate(Date startDate) {
      this.startDate = startDate;
   }

   public Date getEndDate() {
      return this.endDate;
   }

   public void setEndDate(Date endDate) {
      this.endDate = endDate;
   }

   public String getPlatformClass() {
      return this.platformClass;
   }

   public void setPlatformClass(String platformClass) {
      this.platformClass = platformClass;
   }

   public String getPlatformCode() {
      return this.platformCode;
   }

   public void setPlatformCode(String platformCode) {
      this.platformCode = platformCode;
   }

   public String getStartingHarbor() {
      return this.startingHarbor;
   }

   public void setStartingHarbor(String startingHarbor) {
      this.startingHarbor = startingHarbor;
   }

   public String getArrivalHarbor() {
      return this.arrivalHarbor;
   }

   public void setArrivalHarbor(String arrivalHarbor) {
      this.arrivalHarbor = arrivalHarbor;
   }

   public String toStringSSR() {
      Namespace ns_ssr = Namespace.getNamespace("ssr", "http://www.eurofleets.eu/");
      XmlDocument cruise_element = new XmlDocument();
      Element root = new Element("cruise_information");
      Date revision = new Date();
      SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
      String end_str = df2.format(revision);
      root.setAttribute("revision_date", end_str);
      root.setNamespace(ns_ssr);
      cruise_element.addRoot(root);
      Element el1 = new Element("cruise_id");
      el1.setNamespace(ns_ssr);
      if (this.getCruiseId() != null) {
         el1.setText(this.getCruiseId().toString());
      }

      root.addContent(el1);
      Element el2 = new Element("cruise_start_date");
      el2.setNamespace(ns_ssr);
      if (this.getStartDate() != null) {
         el2.setText(this.getStartDate().replace(" ", "T"));
      }

      root.addContent(el2);
      return cruise_element.outString();
   }
}
    