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
    // �����ظ�����ǩ
    if (news.hasTag(tag)) {
      return false;
    }

    ClassAndTagChoiceManager manager = news.getManager();

    // ��������±�ǩ�������Ŵ�δ���������ѷ���
    if (ClassCatalog.getInstance().get("�Ƿ����") != null) {
      Tag unsort = ClassCatalog.getInstance().get("�Ƿ����").getTag("δ����");
      if (news.hasTag(unsort) && tag.getParent() != ClassCatalog.getInstance().get("��������")) {
        Tag sort = ClassCatalog.getInstance().get("�Ƿ����").getTag("�ѷ���");
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
