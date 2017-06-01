package teamwork.loader;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import sun.misc.BASE64Decoder;
import teamwork.model.News;
import teamwork.util.XMLParser;

public class NewsLoader {

  private XMLParser parser = new XMLParser();

  public boolean loadFrom(String filename) {
    return parser.loadFrom(filename, "NewsData");
  }

  public boolean hasNext() {
    return parser.hasNext();
  }

  public News next() {
    Node node = parser.next();

    if (node == null) return null;

    News news = new News();
    NodeList childNode = node.getChildNodes();
    for (int i = 0; i < childNode.getLength(); ++i) {
      Node n = childNode.item(i);
      String nodeName = n.getNodeName();
      String nodeValue = n.getTextContent();
      if (nodeName.equals("Title"))news.setTitle(nodeValue);
      if (nodeName.equals("Date")) news.setDate(nodeValue);
      if (nodeName.equals("Location")) news.setLocation(nodeValue);
      if (nodeName.equals("ID")) news.setId(nodeValue);
      if (nodeName.equals("Type")) news.setType(nodeValue);
      if (nodeName.equals("TrueUrl")) news.setUrl(nodeValue);
      if (nodeName.equals("EncodedContent")) news.setContent(decodeContent(nodeValue));
    }
    return news;
  }

  // 解析EncodedContent，使其成为可读字符串
  private String decodeContent(String encodedContent) {
    byte[] b = null;
    BASE64Decoder decoder = new BASE64Decoder();

    try {
      b = decoder.decodeBuffer(encodedContent);
    } catch (IOException e) {
      e.printStackTrace();
    }

    // 奇偶交换数组中的元素
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
    // 除去头尾的标签
    str = str.replace("<body>", "").replace("</body>", "").replace("<P>", "").replace("</P>", "");
    str = str.replace("<html>", "").replace("</html>", "");
    return str;
  }
}
