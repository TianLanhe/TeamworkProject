package teamwork.model.controler;

import teamwork.model.ClassAndTagChoiceManager;
import teamwork.model.ClassCatalog;
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
    // 不能重复贴标签
    if (news.hasTag(tag)) {
      return false;
    }

    ClassAndTagChoiceManager manager = news.getManager();

    // 如果贴上新标签，将新闻从未分类移至已分类
    if (ClassCatalog.getInstance().get("是否分类") != null) {
      Tag unsort = ClassCatalog.getInstance().get("是否分类").getTag("未分类");
      if (news.hasTag(unsort) && tag.getParent() != ClassCatalog.getInstance().get("报道数量")) {
        Tag sort = ClassCatalog.getInstance().get("是否分类").getTag("已分类");
        manager.alterChoiceToTag(sort);
        unsort.removeNews(news);
        sort.addNews(news);
      }
    }

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
