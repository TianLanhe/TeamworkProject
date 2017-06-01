package teamwork.transaction;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import teamwork.model.ClassCatalog;
import teamwork.model.NewsClass;
import teamwork.model.Tag;

public class LoadAddTagTransaction extends LoadTransaction {

  public LoadAddTagTransaction(Node node) {
    super(node);
  }

  @Override
  protected void parseNode(Node node) {
    NodeList childNode = node.getChildNodes();
    for (int i = 0; i < childNode.getLength(); ++i) {
      Node n = childNode.item(i);
      if (n.getNodeName().equals("Tag")) {
        NamedNodeMap attrs = n.getAttributes();

        String className = attrs.getNamedItem("parent").getNodeValue();
        String tagName = attrs.getNamedItem("name").getNodeValue();

        NewsClass c = ClassCatalog.getInstance().get(className);
        c.addTag(new Tag(tagName, c));
        break;
      }
    }

  }
}
