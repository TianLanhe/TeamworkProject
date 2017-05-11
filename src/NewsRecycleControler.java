package teamwork.controler;

import teamwork.model.News;
import teamwork.model.NewsCatalog;
import teamwork.model.Tag;
import teamwork.model.RecycleBin;

public class NewsRecycleControler {
  RecycleBin recycleBin;
  NewsCatalog newsCatalog;
  Tag tag;
  News news;
  
  public NewsRecycleControler(RecycleBin recycleBin,NewsCatalog newsCatalog,Tag tag) {
  this.recycleBin=recycleBin;
  this.newsCatalog=newsCatalog;
  this.tag=tag;
  }

  public boolean recycleNews(News news) {

    recycleBin.addNews(news);
    newsCatalog.remove(news);
    tag.removeNews(news);

    return true;
  }

}

