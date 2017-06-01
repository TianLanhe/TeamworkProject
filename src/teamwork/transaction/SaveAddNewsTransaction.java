package teamwork.transaction;

import org.w3c.dom.Element;

import teamwork.model.News;
import teamwork.util.XMLCreator;

public class SaveAddNewsTransaction extends SaveTransaction {

  private News news;

  public SaveAddNewsTransaction(XMLCreator creator, News news) {
    super(creator);
    this.news = news;
  }

  @Override
  protected Element createElement() {
    Element element = creator.getNewElement("Transaction");
    element.setAttribute("name", Transaction.ADD_NEWS);

    Element titleEle = creator.getNewElement("Title");
    titleEle.setTextContent(news.getTitle());

    Element contentEle = creator.getNewElement("Content");
    contentEle.setTextContent(news.getContent());

    Element typeEle = creator.getNewElement("Type");
    typeEle.setTextContent(news.getType());

    Element locationEle = creator.getNewElement("Location");
    locationEle.setTextContent(news.getLocation());

    Element dateEle = creator.getNewElement("Date");
    dateEle.setTextContent(news.getDate());

    Element urlEle = creator.getNewElement("Url");
    urlEle.setTextContent(news.getUrl());

    Element idEle = creator.getNewElement("Id");
    idEle.setTextContent(news.getId());

    element.appendChild(titleEle);
    element.appendChild(idEle);
    element.appendChild(urlEle);
    element.appendChild(dateEle);
    element.appendChild(locationEle);
    element.appendChild(typeEle);
    element.appendChild(contentEle);

    return element;
  }

}
