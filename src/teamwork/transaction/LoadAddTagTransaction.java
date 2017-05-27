package teamwork.transaction;

import teamwork.model.ClassCatalog;
import teamwork.model.NewsClass;
import teamwork.model.Tag;

public class LoadAddTagTransaction extends LoadTransaction {

  public LoadAddTagTransaction(String str) {
    super(str);
  }

  @Override
  protected void parseCommand(String command) {
    String[] words = command.split(" ");

    String className = words[1];
    String tagName = words[2];

    NewsClass c = ClassCatalog.getInstance().get(className);
    c.addTag(new Tag(tagName, c));
  }

}
