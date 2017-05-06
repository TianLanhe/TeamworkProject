package teamwork.controler;

import teamwork.model.News;
import teamwork.model.Tag;

public class PostTagMediator {

  private News news;
  private Tag tag;

  public PostTagMediator(News news, Tag tag) {
    this.news = news;
    this.tag = tag;
  }

  public boolean post() {
    if (news.hasTag(tag)) {
      return false;
    }
    news.getTagsList().add(tag);
    tag.addNews(news);
    return true;
  }
}
