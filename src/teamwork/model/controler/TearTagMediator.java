package teamwork.model.controler;

import teamwork.model.ClassAndTagChoiceManager;
import teamwork.model.ClassCatalog;
import teamwork.model.News;
import teamwork.model.Tag;

public class TearTagMediator {

  private News news;
  private Tag tag;

  public TearTagMediator(News news, Tag tag) {
    this.news = news;
    this.tag = tag;
  }

  public boolean tear() {
    // 没有该标签
    if (!news.hasTag(tag)) {
      return false;
    }
    // 不能移除"是否分类"和"报道数量"的标签
    if (tag.getParent() == ClassCatalog.getInstance().get("是否分类")
        || tag.getParent() == ClassCatalog.getInstance().get("报道数量")) {
      return false;
    }

    ClassAndTagChoiceManager manager = news.getManager();

    manager.removeTag(tag);
    tag.removeNews(news);

    // 如果只剩下"报道数量"和"已分类"两个标签，则将新闻从已分类移至未分类
    if (news.sizeTag() == 2) {
      Tag unsort = ClassCatalog.getInstance().get("是否分类").getTag("未分类");
      Tag sort = manager.alterChoiceToTag(unsort);
      sort.removeNews(news);
      unsort.addNews(news);
    }
    return true;
  }

}
