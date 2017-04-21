package teamwork.controler;

import teamwork.model.News;
import teamwork.model.NewsCatalog;

public class NewsLoaderControler {
  private NewsLoader newsLoader;

  public NewsLoaderControler(NewsLoader newsLoader) {
    this.newsLoder = newsLoader;
  }

  public boolean loadData(String file) {
    NewsCatalog newsCatalog = NewsCatalog.getInstance();
    newsLoder.loadFrom(file);

    News news;
    while (newsLoder.hasNext()) {
      news = newsLoder.next();
      newsCatalog.add(news);
    }

    return true;
  }
}
