package dto;


import java.text.SimpleDateFormat;
import java.util.Date;
import org.jdom.Element;
import org.jdom.Namespace;

public class Event {
   private Long id;
   private Date timeStamp;
   private String actor;
   private String subject;
   private String actionName;
   private String actionProperty;
   private String categoryName;
   private Long toolId;
   private String eventId;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Date getTimeStamp() {
      return this.timeStamp;
   }

   public void setTimeStamp(Date timeStamp) {
      this.timeStamp = timeStamp;
   }

   public String getActor() {
      return this.actor;
   }

   public void setActor(String actor) {
      this.actor = actor;
   }

   public String getSubject() {
      return this.subject;
   }

   public void setSubject(String subject) {
      this.subject = subject;
   }

   public String getActionName() {
      return this.actionName;
   }

   public void setActionName(String actionName) {
      this.actionName = actionName;
   }

   public String getActionProperty() {
      return this.actionProperty;
   }

   public void setActionProperty(String actionProperty) {
      this.actionProperty = actionProperty;
   }

   public String getCategoryName() {
      return this.categoryName;
   }

   public void setCategoryName(String categoryName) {
      this.categoryName = categoryName;
   }

   public Long getToolId() {
      return this.toolId;
   }

   public void setToolId(Long toolId) {
      this.toolId = toolId;
   }

   public String getEventId() {
      return this.eventId;
   }

   public void setEventId(String eventId) {
      this.eventId = eventId;
   }

   public String toString24hEvt() {
      Namespace ns_ssr = Namespace.getNamespace("ssr", "http://www.eurofleets.eu/");
      XmlDocument event_element = new XmlDocument();
      Element root = new Element("daily_event_summary");
      root.setNamespace(ns_ssr);
      Date revision = new Date();
      SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
      String end_str = df2.format(revision);
      Element el1 = new Element("daily_event_report");
      el1.setNamespace(ns_ssr);
      el1.setAttribute("revision_date", end_str);
      el1.setAttribute("xlink_href", "./24hEvtSml");
      root.addContent(el1);
      event_element.addRoot(root);
      return event_element.outString();
   }
}