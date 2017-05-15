package teamwork.model.controler;

import teamwork.model.BinCatalog;
import teamwork.model.News;
import teamwork.model.Tag;

public class NewsDeleteControler {
  private News news;

  public NewsDeleteControler(News news) {
    this.news = news;
  }

  public boolean delete() {
    for (Tag tag : news.getTags())
      tag.removeNews(news);
    return BinCatalog.getInstance().add(news);
  }

  public void revoke() {
    for (Tag tag : news.getTags())
      tag.addNews(news);
    BinCatalog.getInstance().remove(news);
  }
}
