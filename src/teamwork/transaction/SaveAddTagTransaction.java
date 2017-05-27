package teamwork.transaction;

import java.io.PrintWriter;

import teamwork.model.Tag;

public class SaveAddTagTransaction extends SaveTransaction {
  private Tag tag;

  public SaveAddTagTransaction(PrintWriter p, Tag t) {
    super(p);
    tag = t;
  }

  @Override
  protected String getCommandString() {
    return "addTag " + tag.getParent().getName() + " " + tag.getName();
  }

}
