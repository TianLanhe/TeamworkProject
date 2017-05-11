package teamwork.model.controler;

import teamwork.model.ClassAndTagChoiceManager;
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
    ClassAndTagChoiceManager manager = news.getManager();
    if (news.hasClass(tag.getParent()) && !tag.getParent().isMultChoice()) {
      Tag oldTag = manager.alterChoiceToTag(tag);
      oldTag.removeNews(news);
    } else {
      manager.addWithoutCheck(tag);
    }
    tag.addNews(news);
    return true;
  }
}
