package teamwork.transaction;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import teamwork.model.News;

public class LoadAddNewsTransaction extends LoadTransaction {

  public LoadAddNewsTransaction(Node node) {
    super(node);
  }

  public LoadAddNewsTransaction(Node node, String newsTagName, String classTagName) {
    super(node, newsTagName, classTagName);
  }

  @Override
  protected void parseNode(Node node) {
    News news = new News();
    NodeList childNode = node.getChildNodes();
    for (int i = 0; i < childNode.getLength(); ++i) {
      Node n = childNode.item(i);
      String nodeName = n.getNodeName();
      String nodeValue = n.getTextContent();
      if (nodeName.equals("Title")) news.setTitle(nodeValue);
      if (nodeName.equals("Date")) news.setDate(nodeValue);
      if (nodeName.equals("Location")) news.setLocation(nodeValue);
      if (nodeName.equals("Id")) news.setId(nodeValue);
      if (nodeName.equals("Type")) news.setType(nodeValue);
      if (nodeName.equals("Url")) news.setUrl(nodeValue);
      if (nodeName.equals("Content")) news.setContent(nodeValue);
    }
    getNewsCatalog().add(news);
  }

}
