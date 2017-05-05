package teamwork.model;

import java.util.ArrayList;
import java.util.List;

public class ClassCatalog {
  private static ClassCatalog instance = null;

  private List<NewsClass> list;

  private ClassCatalog() {
    list = new ArrayList<NewsClass>();
  }

  public static ClassCatalog getInstance() {
    if (instance == null) {
      instance = new ClassCatalog();
    }
    return instance;
  }

  public boolean add(NewsClass c) {
    if (!list.contains(c)) {
      return list.add(c);
    }
    return true;
  }

  public void remove(int i) {
    list.remove(i);
  }

  public void remove(NewsClass c) {
    list.remove(c);
  }

  public boolean contains(NewsClass c) {
    return list.contains(c);
  }

  public int size() {
    return list.size();
  }

  public NewsClass get(int i) {
    return list.get(i);
  }

  public NewsClass get(String name) {
    for (NewsClass c : list)
      if (c.getName().equals(name)) return c;
    return null;
  }

  public int indexOf(NewsClass c) {
    return list.indexOf(c);
  }

  public List<NewsClass> getClassRelatedToTag() {
    List<NewsClass> l = new ArrayList<NewsClass>();
    for (NewsClass c : list) {
      if (c.isRelatedToTag()) l.add(c);
    }
    return l;
  }

  public List<NewsClass> getClassNotRelatedToTag() {
    List<NewsClass> l = new ArrayList<NewsClass>();
    for (NewsClass c : list) {
      if (!c.isRelatedToTag()) l.add(c);
    }
    return l;
  }

  public List<NewsClass> getClassList() {
    return list;
  }

  public void setClassList(List<NewsClass> list) {
    this.list = list;
  }
}
