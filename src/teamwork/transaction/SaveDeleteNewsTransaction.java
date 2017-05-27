package teamwork.transaction;

import org.w3c.dom.Element;

import teamwork.model.News;
import teamwork.util.XMLCreator;

public class SaveDeleteNewsTransaction extends SaveTransaction {

  private News news;

  public SaveDeleteNewsTransaction(XMLCreator creator, News news) {
    super(creator);
    this.news = news;
  }

  @Override
  protected Element createElement() {
    Element element = creator.getNewElement("Transaction");
    element.setAttribute("name", Transaction.DELETE_NEWS);
    element.setAttribute("id", news.getId());
    return element;
  }

}
