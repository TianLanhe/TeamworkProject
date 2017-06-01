package teamwork.util;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLCreator {

  private Document document;// XML文本
  private Element root;// 根节点

  public XMLCreator(String string) {
    try {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      document = db.newDocument();
      document.setXmlStandalone(true);
      root = document.createElement(string);
      document.appendChild(root);
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    }
  }

  public void appendChild(Element element) {
    root.appendChild(element);
  }

  public Element getNewElement(String name) {
    return document.createElement(name);
  }

  public void output(String filename) {
    output(new File(filename));
  }

  public void output(File file) {
    try {
      TransformerFactory tff = TransformerFactory.newInstance();
      Transformer tf = tff.newTransformer();
      tf.setOutputProperty(OutputKeys.INDENT, "yes");// 设置输出换行
      tf.transform(new DOMSource(document), new StreamResult(file));// 输出到文本文件
    } catch (TransformerConfigurationException e) {
      e.printStackTrace();
    } catch (TransformerException e) {
      e.printStackTrace();
    }
  }

}
