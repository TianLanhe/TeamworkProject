package teamwork.transaction;

import java.io.PrintWriter;

import teamwork.model.Tag;

public class SaveTagRelationTransaction extends SaveTransaction {

  private Tag tag;

  public SaveTagRelationTransaction(PrintWriter p, Tag t) {
    super(p);
    tag = t;
  }

  @Override
  protected String getCommandString() {
    return "addRelation " + tag.getParent().getName() + " " + tag.getName() + " "
        + tag.getNextClass().getName();
  }

}
