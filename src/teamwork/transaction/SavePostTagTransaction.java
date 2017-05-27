package teamwork.transaction;

import java.io.PrintWriter;

import teamwork.model.News;
import teamwork.model.Tag;

public class SavePostTagTransaction extends SaveTransaction {

  private News news;
  private Tag tag;

  public SavePostTagTransaction(PrintWriter p, News news, Tag tag) {
    super(p);
    this.tag = tag;
    this.news = news;
  }

  @Override
  protected String getCommandString() {
    return "postTag " + news.getId() + " " + tag.getParent().getName() + " " + tag.getName();
  }

}
