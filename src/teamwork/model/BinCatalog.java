package teamwork.model;


import java.util.ArrayList;
import java.util.List;

public class BinCatalog {

  private static BinCatalog instance = null;

  private List<News> newsList;

  private BinCatalog() {
    newsList = new ArrayList<News>();
  }

  public static BinCatalog getInstance() {
    if (instance == null) {
      instance = new BinCatalog();
    }
    return instance;
  }

  public boolean add(News news) {
    if (!newsList.contains(news)) {
      return newsList.add(news);
    }
    return true;
  }

  public void remove(News news) {
    newsList.remove(news);
  }

  public void remove(int index) {
    newsList.remove(index);
  }

  public int size() {
    return newsList.size();
  }

  public boolean contains(News news) {
    return newsList.contains(news);
  }

  public News get(int i) {
    return newsList.get(i);
  }

  public int indexOf(News news) {
    return newsList.indexOf(news);
  }

  // ///////////////////////
  // ///////////////////////
  public List<News> getNewsList() {
    return newsList;
  }

  public void setNewsList(List<News> newsList) {
    this.newsList = newsList;
  }
}
