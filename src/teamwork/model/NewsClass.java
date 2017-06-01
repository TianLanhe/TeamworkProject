package teamwork.model;

import java.util.ArrayList;
import java.util.List;

public class NewsClass{
  
  private String name;
  private List<Tag> tagsList;
  private boolean isMultChoice;// 该类别是否是多选

  public NewsClass(String name, boolean flag) {
    this.name = name;
    isMultChoice = flag;
    tagsList = new ArrayList<Tag>();
  }

  public NewsClass(String name) {
    this(name, false);
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

  public void removeTag(String tagName) {
    tagsList.remove(new Tag(tagName,this));
  }

  public int indexOfTag(Tag tag) {
    return tagsList.indexOf(tag);
  }

  public int indexOfTag(String tagName) {
    return tagsList.indexOf(new Tag(tagName,this));
  }

  public boolean containsTag(Tag tag) {
    return tagsList.contains(tag);
  }

  public boolean containsTag(String tagName) {
    return containsTag(new Tag(tagName,this));
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

  public int sizeTag() {
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

  // //////////////////////////////////////////////
  // //////////////////////////////////////////////
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

  public boolean isMultChoice() {
    return isMultChoice;
  }

  public void setMultChoice(boolean multChoice) {
    this.isMultChoice = multChoice;
  }
}
