package teamwork.loader;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import sun.misc.BASE64Decoder;
import teamwork.model.News;

public class NewsLoader {
  private NodeList nodes;

  private int index = 0;// ָʾ��ǰ��ȡ���������±�
  private int length = 0;// ����������

  public boolean loadFrom(String filename) {
    try {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document document;
      
      index = 0;
      length = 0;

      // �����ļ������ɹ���ȡ������News�ڵ��������ã�������index��length
      if ((document = db.parse(new FileInputStream(new File(filename)))) != null) {
        nodes = document.getElementsByTagName("NewsData");

        index = 0;
        length = nodes.getLength();
        return true;
      }
    } catch (ParserConfigurationException e) {
      return false;
    } catch (SAXException e) {
      return false;
    } catch (FileNotFoundException e) {
      return false;
    } catch (IOException e) {
      return false;
    }
    return false;
  }

  public boolean hasNext() {
    return index != length;
  }

  public News next() {
    if (index == length) {
      return null;
    }

    News news = new News();

    Node node = nodes.item(index);
    ++index;
    NodeList childNode = node.getChildNodes();
    for (int i = 0; i < childNode.getLength(); ++i) {
      Node n = childNode.item(i);
      String nodeName = n.getNodeName();
      String nodeValue = n.getTextContent();
      if (nodeName.equals("Title")) news.setTitle(nodeValue);
      if (nodeName.equals("Date")) news.setDate(nodeValue);
      if (nodeName.equals("Location")) news.setLocation(nodeValue);
      if (nodeName.equals("ID")) news.setId(nodeValue);
      if (nodeName.equals("Type")) news.setType(nodeValue);
      if (nodeName.equals("TrueUrl")) news.setUrl(nodeValue);
      if (nodeName.equals("EncodedContent")) news.setContent(decodeContent(nodeValue));
    }
    return news;
  }

  // ����EncodedContent��ʹ���Ϊ�ɶ��ַ���
  private String decodeContent(String encodedContent) {
    byte[] b = null;
    BASE64Decoder decoder = new BASE64Decoder();

    try {
      b = decoder.decodeBuffer(encodedContent);
    } catch (IOException e) {
      e.printStackTrace();
    }

    // ��ż���������е�Ԫ��
    for (int i = 0; i < b.length; i += 2) {
      byte m = b[i];
      b[i] = b[i + 1];
      b[i + 1] = m;
    }

    String str = null;
    try {
      str = new String(b, "utf-16");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    //��ȥͷβ�ı�ǩ
    str = str.replace("<body>", "").replace("</body>", "").replace("<P>", "").replace("</P>", "");
    str = str.replace("<html>", "").replace("</html>", "");
    return str;
  }
}
