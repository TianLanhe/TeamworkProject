package teamwork.controler;

import teamwork.model.NewsClass;
import teamwork.model.Tag;

public class ClassTagRelationMediator {

  private Tag tag;
  private NewsClass newsClass;

  public ClassTagRelationMediator(NewsClass c, Tag t) {
    tag = t;
    newsClass = c;
  }

  public boolean addRelation() {
    tag.setNextClass(newsClass);
    newsClass.addRelationToTag();
    return true;
  }

  public boolean removeRelation() {
    if (tag.getNextClass() != newsClass) {
      return false;
    }
    tag.setNextClass(null);
    newsClass.removeRelationToTag();
    return true;
  }
}
