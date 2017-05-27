package teamwork.transaction;

import teamwork.model.ClassCatalog;
import teamwork.model.NewsClass;

public class LoadAddNewsClassTransaction extends LoadTransaction {

  public LoadAddNewsClassTransaction(String str) {
    super(str);
  }

  @Override
  protected void parseCommand(String command) {
    String[] words = command.split(" ");

    String name = words[1];
    boolean isMult = Boolean.parseBoolean(words[2]);

    ClassCatalog.getInstance().add(new NewsClass(name, isMult));
  }

}
