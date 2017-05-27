package teamwork.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {

  private NodeList nodes;

  private int index = 0;// ָʾ��ǰ��ȡ���Ľڵ��±�
  private int length = 0;// �ڵ�������

  public XMLParser() {}

  public XMLParser(String filename, String tagName) {
    loadFrom(filename, tagName);
  }

  public XMLParser(File file, String tagName) {
    loadFrom(file, tagName);
  }

  public boolean loadFrom(String filename, String tagName) {
    return loadFrom(new File(filename), tagName);
  }

  public boolean loadFrom(File file, String tagName) {
    try {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document document;

      index = 0;
      length = 0;

      // �����ļ������ɹ���ȡ�����нڵ��������ã�������index��length
      if ((document = db.parse(new FileInputStream(file))) != null) {
        nodes = document.getElementsByTagName(tagName);

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

  public Node next() {
    if (index == length)
      return null;
    else
      return nodes.item(index++);
  }
}
