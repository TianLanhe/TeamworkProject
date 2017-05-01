package teamwork.model;

import java.util.ArrayList;
import java.util.List;

public class Tag {
  private List<News> newsList;
  private NewsClass nextClass;
  private String name;

  public Tag(String name, NewsClass nextClass) {
    this.name = name;
    this.nextClass = nextClass;
    newsList = new ArrayList<News>();
  }

  public Tag(String name) {
    this(name, null);
  }

  public boolean hasNextClass() {
    return nextClass != null;
  }

  public boolean addNews(News news) {
    if (!newsList.contains(news)) {
      return newsList.add(news);
    }
    return true;
  }

  public void removeNews(News news) {
    newsList.remove(news);
  }

  public void removeNews(int index) {
    newsList.remove(index);
  }

  public int newsSize() {
    return newsList.size();
  }

  public boolean contains(News news) {
    return newsList.contains(news);
  }

  public News getNews(int i) {
    return newsList.get(i);
  }

  public int indexOfNews(News news) {
    return newsList.indexOf(news);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj != null && obj.getClass() == this.getClass()) {
      Tag tag = (Tag) obj;
      return name.equals(tag.name);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }

  @Override
  public String toString() {
    return name;
  }

  public List<News> getNewsList() {
    return newsList;
  }

  public void setNewsList(List<News> newsList) {
    this.newsList = newsList;
  }

  public NewsClass getNextClass() {
    return nextClass;
  }

  public void setNextClass(NewsClass nextClass) {
    this.nextClass = nextClass;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
