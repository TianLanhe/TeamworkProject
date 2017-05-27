package teamwork.transaction;

import org.w3c.dom.Node;

import teamwork.model.NewsCatalog;

public class LoadDeleteNewsTransaction extends LoadTransaction {

  public LoadDeleteNewsTransaction(Node node) {
    super(node);
  }

  @Override
  protected void parseNode(Node node) {
    String id = node.getAttributes().getNamedItem("id").getNodeValue();
    NewsCatalog.getInstance().get(id).delete();
  }
}
