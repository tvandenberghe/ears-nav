package dto;


import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class XmlDocument {
   DocumentBuilderFactory docFactory = null;
   DocumentBuilder docBuilder = null;
   Document doc = null;
   Element searched = null;
   File file_ref;

   public XmlDocument() {
      this.searched = new Element("search");
      this.doc = new Document();
   }

   public Document getDoc() {
      return this.doc;
   }

   public void addRoot(Element r) {
      this.doc.setRootElement(r);
   }

   public void loadXML(String file) throws Exception {
      this.file_ref = new File(file);
      SAXBuilder builder = new SAXBuilder();
      builder.setValidation(false);
      builder.setIgnoringElementContentWhitespace(false);

      try {
         this.doc = builder.build(this.file_ref);
      } catch (JDOMException var4) {
         Logger.getLogger(XmlDocument.class.getName()).log(Level.SEVERE, (String)null, var4);
         throw new JDOMException();
      } catch (IOException var5) {
         Logger.getLogger(XmlDocument.class.getName()).log(Level.SEVERE, (String)null, var5);
         throw new IOException();
      }
   }

   public void loadXML(URL uri) throws Exception {
      SAXBuilder builder = new SAXBuilder();
      builder.setValidation(false);
      builder.setIgnoringElementContentWhitespace(false);

      try {
         this.doc = builder.build(uri);
      } catch (JDOMException var4) {
         Logger.getLogger(XmlDocument.class.getName()).log(Level.SEVERE, (String)null, var4);
         throw new JDOMException();
      } catch (IOException var5) {
         Logger.getLogger(XmlDocument.class.getName()).log(Level.SEVERE, (String)null, var5);
         throw new IOException();
      }
   }

   public void loadXMLfromString(String str) throws Exception {
      SAXBuilder builder = new SAXBuilder();
      builder.setValidation(false);
      builder.setIgnoringElementContentWhitespace(false);

      try {
         this.doc = builder.build(new ByteArrayInputStream(str.getBytes("UTF-8")));
      } catch (JDOMException var4) {
         Logger.getLogger(XmlDocument.class.getName()).log(Level.SEVERE, (String)null, var4);
         throw new JDOMException();
      } catch (IOException var5) {
         Logger.getLogger(XmlDocument.class.getName()).log(Level.SEVERE, (String)null, var5);
         throw new IOException();
      }
   }

   public void close() {
   }

   public void saveXML(String file) {
      try {
         Format format = Format.getPrettyFormat();
         XMLOutputter outp = new XMLOutputter(format);
         FileOutputStream out = new FileOutputStream(file);
         BufferedOutputStream bou = new BufferedOutputStream(out);
         outp.output(this.doc, bou);
         bou.close();
         out.close();
      } catch (Exception var6) {
         ;
      }

   }

   public void out() {
      try {
         Format format = Format.getPrettyFormat();
         XMLOutputter outp = new XMLOutputter(format);
         outp.output(this.doc, System.out);
      } catch (Exception var3) {
         ;
      }

   }

   public String outString() {
      try {
         Format format = Format.getPrettyFormat();
         XMLOutputter outp = new XMLOutputter(format);
         ByteArrayOutputStream byte1 = new ByteArrayOutputStream();
         outp.output(this.doc, byte1);
         return byte1.toString();
      } catch (Exception var4) {
         return null;
      }
   }

   public String getStringFromDocument() {
      Document root = this.doc.getDocument();
      String s = root.toString();
      return s;
   }

   public void printChildren() {
      Element root = this.doc.getRootElement();
      listChildren(root, 0);
   }

   public static void listChildren(Element current, int depth) {
      printSpaces(depth);
      System.out.println(current.getName());
      List<?> children = current.getChildren();
      Iterator iterator = children.iterator();

      while(iterator.hasNext()) {
         Element child = (Element)iterator.next();
         listChildren(child, depth + 1);
      }

   }

   private static void printSpaces(int n) {
      for(int i = 0; i < n; ++i) {
         System.out.print('_');
      }

   }

   public Element getChildren(String node_name, Element current) {
      List<?> children = current.getChildren();
      Iterator iterator = children.iterator();

      while(iterator.hasNext()) {
         Element child = (Element)iterator.next();
         if (child.getName().compareTo(node_name) == 0) {
            this.searched = child;
            return child;
         }

         this.getChildren(node_name, child);
      }

      return this.searched;
   }

   public Element getChildrenByValue(String value_name, Element current) {
      List<?> children = current.getChildren();
      Iterator iterator = children.iterator();

      while(iterator.hasNext()) {
         Element child = (Element)iterator.next();
         if (child.getValue().compareTo(value_name) == 0) {
            this.searched = child;
            return child;
         }

         this.getChildrenByValue(value_name, child);
      }

      return this.searched;
   }

   public Element getNodeByValue(String _name) {
      this.searched = null;
      Element e = null;
      Element root = this.doc.getRootElement();
      if (this.doc != null) {
         e = this.getChildrenByValue(_name, root);
         if (e != null) {
            return e;
         }
      }

      return e;
   }

   public Element getNode(String _name) {
      this.searched = null;
      Element e = null;
      Element root = this.doc.getRootElement();
      if (this.doc != null) {
         e = this.getChildren(_name, root);
         return e;
      } else {
         return e;
      }
   }

   public String getContent(String node_name) {
      Element child = this.getNode(node_name);
      return child != null ? child.getValue() : null;
   }

   public String getContent(String file, String node_name) {
      try {
         this.loadXML(file);
      } catch (Exception var4) {
         Logger.getLogger(XmlDocument.class.getName()).log(Level.SEVERE, (String)null, var4);
      }

      return this.getContent(node_name);
   }

   public Element getRootElement() {
      return this.doc.getRootElement();
   }

   public void changeContent(String file, String _nom_del_tag, String _valor) {
      try {
         this.loadXML(file);
         this.changeContent(_nom_del_tag, _valor);
         this.saveXML(file);
      } catch (Exception var5) {
         var5.printStackTrace();
      }

   }

   public void changeContent(String node_name, String node_value) {
      Element child = this.getNode(node_name);
      if (child != null) {
         child.setText(node_value);
      }

   }

   public boolean addNode(Element node) {
      Element root = this.doc.getRootElement();
      root.addContent(node);
      return true;
   }

   public boolean addNode(String parent_name, String child_name) {
      Element parent = this.getNode(parent_name);
      parent.addContent(new Element(child_name));
      return true;
   }

   public void changeChildContent(String parent_name, String child_name, String child_value) {
      if (this.doc != null) {
         Element parent = this.getNode(parent_name);
         if (parent != null) {
            Element child = parent.getChild(child_name);
            if (child != null) {
               child.setText(child_value);
            }
         }
      }

   }

   public void changeChildContent(String grand_name, String parent_name, String child_name, String child_value) {
      if (this.doc != null) {
         Element grand = this.getNode(grand_name);
         if (grand != null) {
            Element parent = grand.getChild(parent_name);
            if (parent != null) {
               Element child = parent.getChild(child_name);
               if (child != null) {
                  child.setText(child_value);
               }
            }
         }
      }

   }

   public void merge(XmlDocument doc) {
      Element root = this.getRootElement();
      root.addContent((Element)doc.getRootElement().clone());
   }

   public void merge(XmlDocument doc, String _leaf) {
      Element leaf = this.getNode(_leaf);
      leaf.removeContent();
      leaf.addContent(doc.getRootElement().cloneContent());
   }

   public void setTimeStamp(Element el) {
      Date time = new Date();
      time.setTime(System.currentTimeMillis());
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
      Attribute att = el.getAttribute("revision_date");
      if (att != null) {
         att.setValue(df.format(time));
      } else {
         el.setAttribute("revision_date", df.format(time));
      }

   }
}
    