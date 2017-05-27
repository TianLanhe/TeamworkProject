package teamwork.transaction;

import java.io.PrintWriter;

import teamwork.model.News;

public class SaveAddNewsTransaction extends SaveTransaction {

  private News news;

  public SaveAddNewsTransaction(PrintWriter p, News news) {
    super(p);
    this.news = news;
  }

  @Override
  protected String getCommandString() {
    return "addNews " + "url:" + news.getUrl() + " content:" + news.getContent() + " date:"
        + news.getDate() + " id:" + news.getId() + " type:" + news.getType() + " title:"
        + news.getTitle() + " location:" + news.getLocation();
  }

}
