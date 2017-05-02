package teamwork.model;

import java.util.ArrayList;
import java.util.List;

public class NewsCatalog {
  private static NewsCatalog instance = null;

  private List<News> newsList;

  private NewsCatalog() {
    newsList = new ArrayList<News>();
  }

  public List<News> getNewsList() {
    return newsList;
  }

  public void setNewsList(List<News> newsList) {
    this.newsList = newsList;
  }

  public static NewsCatalog getInstance() {
    if (instance == null) {
      instance = new NewsCatalog();
    }
    return instance;
  }

  public boolean updateAll() {
    for (News news : newsList)
      if (!news.update()) {
        return false;
      }
    return true;
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
}
