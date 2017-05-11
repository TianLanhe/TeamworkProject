package teamwork.controler;

import teamwork.model.News;
import teamwork.model.NewsCatalog;
import teamwork.model.Tag;
import teamwork.model.RecycleBin;

public class NewsRecoverControler {
  RecycleBin recycleBin;
  NewsCatalog newsCatalog;
  Tag tag;
  News news;
  
  public NewsRecoverControler(RecycleBin recycleBin,NewsCatalog newsCatalog,Tag tag) {
  this.recycleBin=recycleBin;
  this.newsCatalog=newsCatalog;
  this.tag=tag;
  }

  public boolean recoverNews(News news) {

    recycleBin.remove(news);
    newsCatalog.add(news);
    tag.addNews(news);

    return true;
  }
}
