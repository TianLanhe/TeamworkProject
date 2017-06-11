package teamwork.transaction;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LoadPostTagTransaction extends LoadTransaction {

  public LoadPostTagTransaction(Node node) {
    super(node);
  }

  public LoadPostTagTransaction(Node node, String newsTagName, String classTagName) {
    super(node, newsTagName, classTagName);
  }

  @Override
  protected void parseNode(Node node) {
    String id = node.getAttributes().getNamedItem("id").getNodeValue();

    NodeList childNode = node.getChildNodes();
    for (int i = 0; i < childNode.getLength(); ++i) {
      Node n = childNode.item(i);
      if (n.getNodeName().equals("Tag")) {
        NamedNodeMap attrs = n.getAttributes();

        String className = attrs.getNamedItem("parent").getNodeValue();
        String tagName = attrs.getNamedItem("name").getNodeValue();

        getNewsCatalog().get(id).postTag(getClassCatalog().get(className).getTag(tagName));
        break;
      }
    }
  }

}
