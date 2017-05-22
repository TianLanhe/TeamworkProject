package teamwork.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClassAndTagChoiceManager implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Map<NewsClass, List<Tag>> map;

  public ClassAndTagChoiceManager() {
    map = new HashMap<NewsClass, List<Tag>>();
  }

  public boolean hasClass(NewsClass c) {
    return map.containsKey(c);
  }

  public boolean hasTag(Tag tag) {
    NewsClass c = tag.getParent();
    if (!hasClass(c)) {
      return false;
    } else if (map.get(c).indexOf(tag) == -1) {
      return false;
    }
    return true;
  }

  public void removeTag(Tag tag) {
    NewsClass c = tag.getParent();
    if (!hasClass(c)) {
      return;
    }
    if (map.get(c).size() == 1) {
      map.remove(c);
    } else {
      map.get(c).remove(tag);
    }
  }

  public int classSize() {
    return map.size();
  }

  public int tagSize() {
    int ret = 0;
    Collection<List<Tag>> coll = map.values();
    Iterator<List<Tag>> it = coll.iterator();
    while (it.hasNext()) {
      List<Tag> tagsList = it.next();
      ret += tagsList.size();
    }
    return ret;
  }

  public NewsClass[] getNewsClass() {
    NewsClass[] ret = new NewsClass[map.size()];
    Set<NewsClass> classSet = map.keySet();
    Iterator<NewsClass> it = classSet.iterator();
    int i = 0;
    while (it.hasNext()) {
      ret[i++] = it.next();
    }
    return ret;
  }

  public Tag[] getTags() {
    Tag[] ret = new Tag[tagSize()];
    Collection<List<Tag>> coll = map.values();
    Iterator<List<Tag>> it = coll.iterator();
    int i = 0;
    while (it.hasNext()) {
      List<Tag> tagsList = it.next();
      for (int j = 0; j < tagsList.size(); ++j) {
        ret[i++] = tagsList.get(j);
      }
    }
    return ret;
  }

  public void addWithoutCheck(Tag tag) {
    if (map.containsKey(tag.getParent())) {
      map.get(tag.getParent()).add(tag);
    } else {
      List<Tag> l = new ArrayList<Tag>();
      l.add(tag);
      map.put(tag.getParent(), l);
    }
  }

  public Tag alterChoiceToTag(Tag tag) {
    Tag ret = map.get(tag.getParent()).get(0);
    map.get(tag.getParent()).remove(0);
    map.get(tag.getParent()).add(tag);
    return ret;
  }
}
