package teamwork.transaction;

import java.io.PrintWriter;

import teamwork.model.NewsClass;

public class SaveAddNewsClassTransaction extends SaveTransaction {

  private NewsClass c;

  public SaveAddNewsClassTransaction(PrintWriter p, NewsClass c) {
    super(p);
    this.c = c;
  }

  @Override
  protected String getCommandString() {
    return "addNewsClass " + c.getName() + " " + c.isMultChoice();
  }

}
