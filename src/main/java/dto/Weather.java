package dto;


import java.text.SimpleDateFormat;
import java.util.Date;
import org.jdom.Element;
import org.jdom.Namespace;

public class Weather {
   private Date fecha;
   private Double velocidad_media_viento;
   private Double velocidad_inst_viento;
   private Double direccion_viento;
   private Double temperatura_aire;
   private Double humedad;
   private Double radiacion_solar;
   private Double presion_atm;
   private Double temperatura_agua;
   private Date fecha_instrumento;

   public String getFecha() {
      return this.fecha.toString();
   }

   public void setFecha(Date fecha) {
      this.fecha = fecha;
   }

   public Double getVelocidad_media_viento() {
      return this.velocidad_media_viento;
   }

   public void setVelocidad_media_viento(Double velocidad_media_viento) {
      this.velocidad_media_viento = velocidad_media_viento;
   }

   public Double getVelocidad_inst_viento() {
      return this.velocidad_inst_viento;
   }

   public void setVelocidad_inst_viento(Double velocidad_inst_viento) {
      this.velocidad_inst_viento = velocidad_inst_viento;
   }

   public Double getDireccion_viento() {
      return this.direccion_viento;
   }

   public void setDireccion_viento(Double direccion_viento) {
      this.direccion_viento = direccion_viento;
   }

   public Double getTemperatura_aire() {
      return this.temperatura_aire;
   }

   public void setTemperatura_aire(Double temperatura_aire) {
      this.temperatura_aire = temperatura_aire;
   }

   public Double getHumedad() {
      return this.humedad;
   }

   public void setHumedad(Double humedad) {
      this.humedad = humedad;
   }

   public Double getRadiacion_solar() {
      return this.radiacion_solar;
   }

   public void setRadiacion_solar(Double radiacion_solar) {
      this.radiacion_solar = radiacion_solar;
   }

   public Double getPresion_atm() {
      return this.presion_atm;
   }

   public void setPresion_atm(Double presion_atm) {
      this.presion_atm = presion_atm;
   }

   public Double getTemperatura_agua() {
      return this.temperatura_agua;
   }

   public void setTemperatura_agua(Double temperatura_agua) {
      this.temperatura_agua = temperatura_agua;
   }

   public String getFecha_instrumento() {
      return this.fecha_instrumento.toString();
   }

   public void setFecha_instrumento(Date date) {
      this.fecha_instrumento = date;
   }

   public String toStringSSR() {
      Namespace ns_ssr = Namespace.getNamespace("ssr", "http://www.eurofleets.eu/");
      XmlDocument weather_element = new XmlDocument();
      Element root = new Element("last_weather_data");
      root.setNamespace(ns_ssr);
      Date revision = new Date();
      SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
      String end_str = df2.format(revision);
      root.setAttribute("revision_date", end_str);
      weather_element.addRoot(root);
      Element el1 = new Element("date_time");
      el1.setNamespace(ns_ssr);
      if (this.getFecha() != null) {
         el1.setText(this.getFecha().replace(" ", "T"));
      }

      root.addContent(el1);
      Element el2 = new Element("wind_speed");
      el2.setNamespace(ns_ssr);
      el2.setAttribute("uom", "m/s");
      el2.setAttribute("definition", "ESSAZZ01");
      if (this.getVelocidad_media_viento() != null) {
         el2.setText(this.getVelocidad_media_viento().toString());
      }

      root.addContent(el2);
      Element el3 = new Element("wind_gust");
      el3.setNamespace(ns_ssr);
      el3.setAttribute("uom", "m/s");
      el3.setAttribute("definition", "EGTSS15S");
      if (this.getVelocidad_inst_viento() != null) {
         el3.setText(this.getVelocidad_inst_viento().toString());
      }

      root.addContent(el3);
      Element el4 = new Element("wind_direction");
      el4.setNamespace(ns_ssr);
      el4.setAttribute("definition", "EGTDSS01");
      if (this.getDireccion_viento() != null) {
         el4.setText(this.getDireccion_viento().toString());
      }

      root.addContent(el4);
      Element el5 = new Element("air_temperature");
      el5.setNamespace(ns_ssr);
      el5.setAttribute("uom", "Celsius");
      el5.setAttribute("definition", "CDTA");
      if (this.getTemperatura_aire() != null) {
         el5.setText(this.getTemperatura_aire().toString());
      }

      root.addContent(el5);
      Element el6 = new Element("air_preassure");
      el6.setNamespace(ns_ssr);
      el6.setAttribute("uom", "hPa");
      el6.setAttribute("definition", "CAPH");
      if (this.getPresion_atm() != null) {
         el6.setText(this.getPresion_atm().toString());
      }

      root.addContent(el6);
      Element el7 = new Element("humidity");
      el7.setNamespace(ns_ssr);
      el7.setAttribute("uom", "%");
      el7.setAttribute("definition", "CHUM");
      if (this.getHumedad() != null) {
         el7.setText(this.getHumedad().toString());
      }

      root.addContent(el7);
      Element el8 = new Element("solar_radiation");
      el8.setNamespace(ns_ssr);
      el8.setAttribute("uom", "Wm2");
      el8.setAttribute("definition", "CSLR");
      if (this.getRadiacion_solar() != null) {
         el8.setText(this.getRadiacion_solar().toString());
      }

      root.addContent(el8);
      Element el9 = new Element("surface_water_temperature");
      el9.setNamespace(ns_ssr);
      el9.setAttribute("uom", "Celsius");
      el9.setAttribute("definition", "EMP");
      if (this.getTemperatura_agua() != null) {
         el9.setText(this.getTemperatura_agua().toString());
      }

      root.addContent(el9);
      return weather_element.outString();
   }

   public String toString24hMet() {
      Namespace ns_ssr = Namespace.getNamespace("ssr", "http://www.eurofleets.eu/");
      XmlDocument weather_element = new XmlDocument();
      Element root = new Element("daily_weather_data");
      root.setNamespace(ns_ssr);
      Date revision = new Date();
      SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
      String end_str = df2.format(revision);
      root.setAttribute("xlink_href", "./24hMetJson");
      root.setAttribute("revision_date", end_str);
      weather_element.addRoot(root);
      return weather_element.outString();
   }
}