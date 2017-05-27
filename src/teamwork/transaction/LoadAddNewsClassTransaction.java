package teamwork.transaction;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import teamwork.model.ClassCatalog;
import teamwork.model.NewsClass;

public class LoadAddNewsClassTransaction extends LoadTransaction {

  public LoadAddNewsClassTransaction(Node node) {
    super(node);
  }

  @Override
  protected void parseNode(Node node) {
    NamedNodeMap attrs = node.getAttributes();

    String name = attrs.getNamedItem("className").getNodeValue();
    boolean isMult = Boolean.parseBoolean(attrs.getNamedItem("isMult").getNodeValue());

    ClassCatalog.getInstance().add(new NewsClass(name, isMult));
  }

}
