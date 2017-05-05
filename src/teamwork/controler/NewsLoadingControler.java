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

    // 添加未分类标签
    NewsClass cSort = classCatalog.get("是否分类");
    if (cSort == null) {
      cSort = new NewsClass("是否分类");
      cSort.addTag(new Tag("已分类"));
      cSort.addTag(new Tag("未分类"));
      classCatalog.add(cSort);
    }

    // 添加新闻类别标签
    NewsClass cLocation = classCatalog.get("报道数量");
    if (cLocation == null) {
      cLocation = new NewsClass("报道数量");
      classCatalog.add(cLocation);
    }

    int count = 0;
    News news;
    Tag unsort = cSort.getTag("未分类");
    Tag locationTag;
    while (newsLoader.hasNext()) {
      news = newsLoader.next();

      if (!newsCatalog.contains(news)) {
        news.postTag(unsort);

        String location = news.getLocation().substring(0, news.getLocation().indexOf("报") + 1);
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
