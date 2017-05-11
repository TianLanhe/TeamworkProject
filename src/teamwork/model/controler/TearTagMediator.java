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
    // û�иñ�ǩ
    if (!news.hasTag(tag)) {
      return false;
    }
    // �����Ƴ�"�Ƿ����"��"��������"�ı�ǩ
    if (tag.getParent() == ClassCatalog.getInstance().get("�Ƿ����")
        || tag.getParent() == ClassCatalog.getInstance().get("��������")) {
      return false;
    }

    ClassAndTagChoiceManager manager = news.getManager();

    manager.removeTag(tag);
    tag.removeNews(news);

    // ���ֻʣ��"��������"��"�ѷ���"������ǩ�������Ŵ��ѷ�������δ����
    if (news.sizeTag() == 2) {
      Tag unsort = ClassCatalog.getInstance().get("�Ƿ����").getTag("δ����");
      Tag sort = manager.alterChoiceToTag(unsort);
      sort.removeNews(news);
      unsort.addNews(news);
    }
    return true;
  }

}
