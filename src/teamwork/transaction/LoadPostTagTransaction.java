package teamwork.transaction;

import teamwork.model.ClassCatalog;
import teamwork.model.NewsCatalog;

public class LoadPostTagTransaction extends LoadTransaction {

  public LoadPostTagTransaction(String str) {
    super(str);
  }

  @Override
  protected void parseCommand(String command) {
    String[] words = command.split(" ");

    String id = words[1];
    String className = words[2];
    String tagName = words[3];

    NewsCatalog.getInstance().get(id)
        .postTag(ClassCatalog.getInstance().get(className).getTag(tagName));

  }

}
