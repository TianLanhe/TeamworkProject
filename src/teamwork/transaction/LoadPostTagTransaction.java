package teamwork.transaction;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import teamwork.model.ClassCatalog;
import teamwork.model.NewsCatalog;

public class LoadPostTagTransaction extends LoadTransaction {

  public LoadPostTagTransaction(Node node) {
    super(node);
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

        NewsCatalog.getInstance().get(id)
            .postTag(ClassCatalog.getInstance().get(className).getTag(tagName));
        break;
      }
    }
  }

}
