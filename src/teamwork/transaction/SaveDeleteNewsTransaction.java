package teamwork.transaction;

import java.io.PrintWriter;

import teamwork.model.News;

public class SaveDeleteNewsTransaction extends SaveTransaction {

  private News news;

  public SaveDeleteNewsTransaction(PrintWriter p, News news) {
    super(p);
    this.news = news;
  }

  @Override
  protected String getCommandString() {
    return "deleteNews " + news.getId();
  }

}
