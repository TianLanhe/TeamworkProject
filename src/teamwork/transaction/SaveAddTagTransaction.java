package teamwork.transaction;

import org.w3c.dom.Element;

import teamwork.model.Tag;
import teamwork.util.XMLCreator;

public class SaveAddTagTransaction extends SaveTransaction {
  private Tag tag;

  public SaveAddTagTransaction(XMLCreator creator, Tag t) {
    super(creator);
    tag = t;
  }

  @Override
  protected Element createElement() {
    Element element = creator.getNewElement("Transaction");
    element.setAttribute("name", Transaction.ADD_TAG);

    Element tagEle = creator.getNewElement("Tag");
    tagEle.setAttribute("name", tag.getName());
    tagEle.setAttribute("parent", tag.getParent().getName());

    element.appendChild(tagEle);
    return element;
  }
}
