package teamwork.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassCatalog {
  private static ClassCatalog instance = null;

  private List<NewsClass> list;

  private Map<Tag, NewsClass> tagNextToClass;

  private ClassCatalog() {
    list = new ArrayList<NewsClass>();
    tagNextToClass = new HashMap<Tag, NewsClass>();
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
    list.remove(c);// TODO
  }

  public void remove(String className) {
    list.remove(new NewsClass(className));// TODO
  }

  public boolean contains(NewsClass c) {
    return list.contains(c);
  }

  public boolean contains(String className) {
    return contains(new NewsClass(className));
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

  public int indexOf(String className) {
    return indexOf(new NewsClass(className));
  }

  public List<NewsClass> getClassRelatedToTag() {
    Collection<NewsClass> coll = tagNextToClass.values();
    List<NewsClass> l = new ArrayList<NewsClass>(coll);
    return l;
  }

  public List<NewsClass> getClassNotRelatedToTag() {
    List<NewsClass> l = new ArrayList<NewsClass>();
    for (NewsClass c : list) {
      if (!tagNextToClass.containsValue(c)) {
        l.add(c);
      }
    }
    return l;
  }

  public void addRelation(Tag tag, NewsClass nextClass) {
    if (nextClass == null) {
      if (tagNextToClass.containsKey(tag)) {
        tagNextToClass.remove(tag);
      }
    } else {
      tagNextToClass.put(tag, nextClass);
    }
  }

  // //////////////////////
  // //////////////////////
  public List<NewsClass> getClassList() {
    return list;
  }

  public void setClassList(List<NewsClass> list) {
    this.list = list;
  }

  public void setTagToClassMap(Map<Tag, NewsClass> tagNextToClass) {
    this.tagNextToClass = tagNextToClass;
  }

  public Map<Tag, NewsClass> getTagToClassMap() {
    return tagNextToClass;
  }
}
