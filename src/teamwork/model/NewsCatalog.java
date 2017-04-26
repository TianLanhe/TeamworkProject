package teamwork.model;

import java.util.ArrayList;
import java.util.List;

public class NewsCatalog {
  private static NewsCatalog instance = null;

  private List<News> newsList;

  private NewsCatalog() {
    newsList = new ArrayList<News>();
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

  public void add(News news) {
    if (!newsList.contains(news)) {
      newsList.add(news);
    }
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
}
