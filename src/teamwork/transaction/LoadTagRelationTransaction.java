package teamwork.transaction;

import teamwork.model.ClassCatalog;
import teamwork.model.NewsClass;

public class LoadTagRelationTransaction extends LoadTransaction {

  public LoadTagRelationTransaction(String str) {
    super(str);
  }

  @Override
  protected void parseCommand(String command) {
    String[] words = command.split(" ");

    String className = words[1];
    String tagName = words[2];
    String nextClassName = words[3];

    NewsClass c = ClassCatalog.getInstance().get(className);
    NewsClass nextClass = ClassCatalog.getInstance().get(nextClassName);
    c.getTag(tagName).setNextClass(nextClass);
  }

}
