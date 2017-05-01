package teamwork.model;

import java.util.ArrayList;
import java.util.List;

public class NewsClass {
  private String name;
  private List<Tag> tagsList;

  public NewsClass(String name) {
    this.name = name;
    tagsList = new ArrayList<Tag>();
  }

  public boolean isAncestorOf(NewsClass c) {
    if (c == this) {
      return true;
    } else {
      for (Tag tag : tagsList) {
        if (tag.hasNextClass() && tag.getNextClass().isAncestorOf(c)) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean addTag(Tag tag) {
    if (!tagsList.contains(tag)) {
      return tagsList.add(tag);
    }
    return true;
  }

  public void removeTag(int i) {
    tagsList.remove(i);
  }

  public void removeTag(Tag tag) {
    tagsList.remove(tag);
  }

  public int indexOfTag(Tag tag) {
    return tagsList.indexOf(tag);
  }

  public boolean containsTag(Tag tag) {
    return tagsList.contains(tag);
  }

  public Tag getTag(int i) {
    return tagsList.get(i);
  }

  public Tag getTag(String tagName) {
    for (Tag tag : tagsList) {
      if (tag.getName().equals(tagName)) {
        return tag;
      }
    }
    return null;
  }

  public int tagSize() {
    return tagsList.size();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj != null && obj.getClass() == this.getClass()) {
      NewsClass c = (NewsClass) obj;
      return name.equals(c.name);
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setTagsList(List<Tag> tagsList) {
    this.tagsList = tagsList;
  }

  public List<Tag> getTagsList() {
    return tagsList;
  }
}
