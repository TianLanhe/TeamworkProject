package teamwork.model.controler;

import teamwork.model.ClassAndTagChoiceManager;
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
    if (tag.getParent().getName().equals("�Ƿ����") || tag.getParent().getName().equals("��������")) {
      return false;
    }

    ClassAndTagChoiceManager manager = news.getManager();

    manager.removeTag(tag);
    tag.removeNews(news);

    // ���ֻʣ��"��������"��"�ѷ���"������ǩ�������Ŵ��ѷ�������δ����
    if (news.sizeTag() == 2) {
      Tag unsort = tag.getParent().getParent().get("�Ƿ����").getTag("δ����");
      Tag sort = manager.alterChoiceToTag(unsort);
      sort.removeNews(news);
      unsort.addNews(news);
    }
    return true;
  }

}
