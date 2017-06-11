package teamwork.transaction;

import org.w3c.dom.Node;

public class LoadDeleteNewsTransaction extends LoadTransaction {

  public LoadDeleteNewsTransaction(Node node) {
    super(node);
  }

  public LoadDeleteNewsTransaction(Node node, String newsTagName, String classTagName) {
    super(node, newsTagName, classTagName);
  }

  @Override
  protected void parseNode(Node node) {
    String id = node.getAttributes().getNamedItem("id").getNodeValue();
    getNewsCatalog().get(id).delete();
  }
}
