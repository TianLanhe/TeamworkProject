package teamwork.controler;

import teamwork.loader.NewsLoader;
import teamwork.model.ClassCatalog;
import teamwork.model.News;
import teamwork.model.NewsCatalog;
import teamwork.model.NewsClass;
import teamwork.model.Tag;

public class NewsLoadingControler {
  private NewsLoader newsLoader;

  public NewsLoadingControler(NewsLoader newsLoader) {
    this.newsLoader = newsLoader;
  }

  public int loadData(String file) {
    if (!newsLoader.loadFrom(file)) {
      return -1;
    }

    NewsCatalog newsCatalog = NewsCatalog.getInstance();
    ClassCatalog classCatalog = ClassCatalog.getInstance();

    // ���δ�����ǩ
    NewsClass cSort = classCatalog.get("�Ƿ����");
    if (cSort == null) {
      cSort = new NewsClass("�Ƿ����");
      cSort.addTag(new Tag("�ѷ���"));
      cSort.addTag(new Tag("δ����"));
      classCatalog.add(cSort);
    }

    // �����������ǩ
    NewsClass cLocation = classCatalog.get("��������");
    if (cLocation == null) {
      cLocation = new NewsClass("��������");
      classCatalog.add(cLocation);
    }

    int count = 0;
    News news;
    Tag unsort = cSort.getTag("δ����");
    Tag locationTag;
    while (newsLoader.hasNext()) {
      news = newsLoader.next();

      if (!newsCatalog.contains(news)) {
        news.postTag(unsort);

        String location = news.getLocation().substring(0, news.getLocation().indexOf("��") + 1);
        locationTag = cLocation.getTag(location);
        if (locationTag == null) {
          locationTag = new Tag(location);
          cLocation.addTag(locationTag);
        }

        news.postTag(locationTag);

        newsCatalog.add(news);
        ++count;
      }
    }
    return count;
  }
}
