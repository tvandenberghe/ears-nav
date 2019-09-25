package dto;


import java.text.SimpleDateFormat;
import java.util.Date;
import org.jdom.Element;
import org.jdom.Namespace;

public class Thermosal {
   private Date fecha;
   private Double salinidad;
   private Double temperatura;
   private Double fluor;
   private Double conductividad;
   private Double sigmat;
   private Date fecha_instrumento;

   public String getFecha() {
      return this.fecha.toString();
   }

   public void setFecha(Date fecha) {
      this.fecha = fecha;
   }

   public Double getSalinidad() {
      return this.salinidad;
   }

   public void setSalinidad(Double salinidad) {
      this.salinidad = salinidad;
   }

   public Double getTemperatura() {
      return this.temperatura;
   }

   public void setTemperatura(Double temperatura) {
      this.temperatura = temperatura;
   }

   public Double getFluor() {
      return this.fluor;
   }

   public void setFluor(Double fluor) {
      this.fluor = fluor;
   }

   public Double getConductividad() {
      return this.conductividad;
   }

   public void setConductividad(Double conductividad) {
      this.conductividad = conductividad;
   }

   public Double getSigmat() {
      return this.sigmat;
   }

   public void setSigmat(Double sigmat) {
      this.sigmat = sigmat;
   }

   public String getFecha_instrumento() {
      return this.fecha_instrumento.toString();
   }

   public void setFecha_instrumento(Date fecha_instrumento) {
      this.fecha_instrumento = fecha_instrumento;
   }

   public String toStringSSR() {
      Namespace ns_ssr = Namespace.getNamespace("ssr", "http://www.eurofleets.eu/");
      XmlDocument thermosal_element = new XmlDocument();
      Element root = new Element("last_thermosalinometer_data");
      root.setNamespace(ns_ssr);
      Date revision = new Date();
      SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
      String end_str = df2.format(revision);
      root.setAttribute("revision_date", end_str);
      thermosal_element.addRoot(root);
      Element el1 = new Element("date_time");
      el1.setNamespace(ns_ssr);
      if (this.getFecha() != null) {
         el1.setText(this.getFecha().replace(" ", "T"));
      }

      root.addContent(el1);
      Element el2 = new Element("surface_water_temperature");
      el2.setNamespace(ns_ssr);
      el2.setAttribute("uom", "Celsius");
      el2.setAttribute("definition", "TEMPSD01");
      if (this.getTemperatura() != null) {
         el2.setText(this.getTemperatura().toString());
      }

      root.addContent(el2);
      Element el3 = new Element("salinity");
      el3.setNamespace(ns_ssr);
      el3.setAttribute("uom", "pSu");
      el3.setAttribute("definition", "PSLTZZ01");
      if (this.getSalinidad() != null) {
         el3.setText(this.getSalinidad().toString());
      }

      root.addContent(el3);
      Element el4 = new Element("conductivity");
      el4.setNamespace(ns_ssr);
      el4.setAttribute("uom", "S/m");
      el4.setAttribute("definition", "CNDCSG01");
      if (this.getConductividad() != null) {
         el4.setText(this.getConductividad().toString());
      }

      root.addContent(el4);
      Element el5 = new Element("sigmaT");
      el5.setNamespace(ns_ssr);
      el5.setAttribute("uom", "kg/m3");
      el5.setAttribute("definition", "SIGTEQST");
      if (this.getSigmat() != null) {
         el5.setText(this.getSigmat().toString());
      }

      root.addContent(el5);
      Element el6 = new Element("raw_fluorometry");
      el6.setNamespace(ns_ssr);
      el6.setAttribute("uom", "volts");
      el6.setAttribute("definition", "FVLTZZ01");
      if (this.getFluor() != null) {
         el6.setText(this.getFluor().toString());
      }

      root.addContent(el6);
      return thermosal_element.outString();
   }

   public String toString24hTss() {
      Namespace ns_ssr = Namespace.getNamespace("ssr", "http://www.eurofleets.eu/");
      XmlDocument thermosal_element = new XmlDocument();
      Element root = new Element("daily_thermosalinometer_data");
      root.setNamespace(ns_ssr);
      System.out.println("raquel - toString24hTss");
      Date revision = new Date();
      SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
      String end_str = df2.format(revision);
      root.setAttribute("revision_date", end_str);
      root.setAttribute("xlink_href", "./24hTssJson");
      thermosal_element.addRoot(root);
      return thermosal_element.outString();
   }
}