package teamwork.controler;

import teamwork.loader.NewsLoader;
import teamwork.model.News;
import teamwork.model.NewsCatalog;

public class NewsLoaderControler {
  private NewsLoader newsLoader;

  public NewsLoaderControler(NewsLoader newsLoader) {
    this.newsLoader = newsLoader;
  }

  public boolean loadData(String file) {
    NewsCatalog newsCatalog = NewsCatalog.getInstance();
    if (!newsLoader.loadFrom(file)) {
      return false;
    }

    News news;
    while (newsLoader.hasNext()) {
      news = newsLoader.next();
      newsCatalog.add(news);
    }

    return true;
  }
}
