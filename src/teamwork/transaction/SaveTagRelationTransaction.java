package teamwork.transaction;

import org.w3c.dom.Element;

import teamwork.model.Tag;
import teamwork.util.XMLCreator;

public class SaveTagRelationTransaction extends SaveTransaction {

  private Tag tag;

  public SaveTagRelationTransaction(XMLCreator c, Tag t) {
    super(c);
    tag = t;
  }

  @Override
  protected Element createElement() {
    Element element = creator.getNewElement("Transaction");
    element.setAttribute("name", Transaction.ADD_RELATION);

    Element tagEle = creator.getNewElement("Tag");
    tagEle.setAttribute("name", tag.getName());
    tagEle.setAttribute("parent", tag.getParent().getName());

    Element classEle = creator.getNewElement("NextClass");
    classEle.setAttribute("name", tag.getNextClass().getName());

    element.appendChild(tagEle);
    element.appendChild(classEle);
    return element;
  }

}
