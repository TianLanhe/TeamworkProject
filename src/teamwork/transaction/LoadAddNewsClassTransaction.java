package teamwork.transaction;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import teamwork.model.NewsClass;

public class LoadAddNewsClassTransaction extends LoadTransaction {

  public LoadAddNewsClassTransaction(Node node) {
    super(node);
  }

  public LoadAddNewsClassTransaction(Node node, String newsTagName, String classTagName) {
    super(node, newsTagName, classTagName);
  }

  @Override
  protected void parseNode(Node node) {
    NamedNodeMap attrs = node.getAttributes();

    String name = attrs.getNamedItem("className").getNodeValue();
    boolean isMult = Boolean.parseBoolean(attrs.getNamedItem("isMult").getNodeValue());

    getClassCatalog().add(new NewsClass(name, isMult));
  }

}
