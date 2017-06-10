package teamwork.transaction;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import teamwork.model.NewsClass;

public class LoadTagRelationTransaction extends LoadTransaction {

  public LoadTagRelationTransaction(Node node) {
    super(node);
  }
  
  public LoadTagRelationTransaction(Node node, String newsTagName, String classTagName) {
    super(node,newsTagName,classTagName);
  }

  @Override
  protected void parseNode(Node node) {
    String className = null;
    String tagName = null;
    String nextClassName = null;
    
    NodeList childNode = node.getChildNodes();
    for (int i = 0; i < childNode.getLength(); ++i) {
      Node n = childNode.item(i);
      if (n.getNodeName().equals("Tag")) {
        NamedNodeMap attrs = n.getAttributes();

        className = attrs.getNamedItem("parent").getNodeValue();
        tagName = attrs.getNamedItem("name").getNodeValue();
      }else if(n.getNodeName().equals("NextClass")){
        nextClassName = n.getAttributes().getNamedItem("name").getNodeValue();
      }
    }
    
    NewsClass c = getClassCatalog().get(className);
    NewsClass nextClass = getClassCatalog().get(nextClassName);
    c.getTag(tagName).setNextClass(nextClass);
  }

}
