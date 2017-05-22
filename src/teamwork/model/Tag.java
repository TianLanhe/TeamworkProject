package teamwork.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tag implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  private List<News> newsList;
  private NewsClass nextClass;
  private NewsClass parent;
  private String name;
  
  public Tag(String name,NewsClass parent,NewsClass next){
    this.name = name;
    this.parent = parent;
    setNextClass(next);
    newsList = new ArrayList<News>();
  }

  public Tag(String name,NewsClass parent) {
    this(name,parent,null);
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

  public News findPrevNews(News news) {
    int index = indexOfNews(news);
    if (index == -1) {
      return null;
    }
    index = Math.max(index - 1, 0);
    return getNews(index);
  }

  public News findNextNews(News news) {
    int index = indexOfNews(news);
    if (index == -1) {
      return null;
    }
    index = Math.min(index + 1, newsSize() - 1);
    return getNews(index);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj != null && obj.getClass() == this.getClass()) {
      Tag tag = (Tag) obj;
      return name.equals(tag.name) && parent.equals(tag.getParent());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = result * 31 + name.hashCode();
    result = result * 31 + parent.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return name;
  }
  
  public void setNextClass(NewsClass nextClass) {
    this.nextClass = nextClass;
    ClassCatalog.getInstance().addRelation(this,nextClass);
  }

  // //////////////////////////////////////////////
  // //////////////////////////////////////////////
  public List<News> getNewsList() {
    return newsList;
  }

  public void setNewsList(List<News> newsList) {
    this.newsList = newsList;
  }

  public NewsClass getNextClass() {
    return nextClass;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public NewsClass getParent() {
    return parent;
  }

  public void setParent(NewsClass parent) {
    this.parent = parent;
  }
}
