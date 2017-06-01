package teamwork.transaction;

import org.w3c.dom.Element;

import teamwork.model.NewsClass;
import teamwork.util.XMLCreator;

public class SaveAddNewsClassTransaction extends SaveTransaction {

  private NewsClass c;

  public SaveAddNewsClassTransaction(XMLCreator creator, NewsClass c) {
    super(creator);
    this.c = c;
  }

  @Override
  protected Element createElement() {
    Element element = creator.getNewElement("Transaction");
    element.setAttribute("name", Transaction.ADD_NEWS_CLASS);
    element.setAttribute("className", c.getName());
    element.setAttribute("isMult", String.valueOf(c.isMultChoice()));
    return element;
  }

}
