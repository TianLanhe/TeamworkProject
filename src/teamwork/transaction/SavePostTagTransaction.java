package teamwork.transaction;

import org.w3c.dom.Element;

import teamwork.model.News;
import teamwork.model.Tag;
import teamwork.util.XMLCreator;

public class SavePostTagTransaction extends SaveTransaction {

  private News news;
  private Tag tag;

  public SavePostTagTransaction(XMLCreator creator, News news, Tag tag) {
    super(creator);
    this.tag = tag;
    this.news = news;
  }

  @Override
  protected Element createElement() {
    Element element = creator.getNewElement("Transaction");
    element.setAttribute("name", Transaction.POST_TAG);
    element.setAttribute("id", news.getId());

    Element tagEle = creator.getNewElement("Tag");
    tagEle.setAttribute("name", tag.getName());
    tagEle.setAttribute("parent", tag.getParent().getName());

    element.appendChild(tagEle);
    return element;
  }

}
