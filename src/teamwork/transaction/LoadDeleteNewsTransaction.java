package teamwork.transaction;

import teamwork.model.NewsCatalog;

public class LoadDeleteNewsTransaction extends LoadTransaction {

  public LoadDeleteNewsTransaction(String str) {
    super(str);
  }

  @Override
  protected void parseCommand(String command) {
    String[] words = command.split(" ");

    String id = words[1];

    NewsCatalog.getInstance().get(id).delete();
  }

}
