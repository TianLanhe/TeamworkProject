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

  public boolean loadData(String file) {
    if (!newsLoader.loadFrom(file)) {
      return false;
    }
    
    NewsCatalog newsCatalog = NewsCatalog.getInstance();
    ClassCatalog classCatalog = ClassCatalog.getInstance();
    
    //添加未分类标签
    NewsClass c = classCatalog.get("是否分类");
    if(c==null){
      NewsClass newClass = new NewsClass("是否分类");
      newClass.addTag(new Tag("已分类"));
      newClass.addTag(new Tag("未分类"));
      c = newClass;
      classCatalog.add(c);
    }
    
    //添加新闻类别标签
    NewsClass cLocation = classCatalog.get("报道数量");
    if(cLocation==null){
      NewsClass newClass = new NewsClass("报道数量");
      newClass.addTag(new Tag("光明日报"));
      newClass.addTag(new Tag("四川日报"));
      newClass.addTag(new Tag("南方都市报"));
      cLocation = newClass;
      classCatalog.add(cLocation);
    }

    News news;
    Tag unsort = c.getTag("未分类");
    Tag locationTag;
    while (newsLoader.hasNext()) {
      news = newsLoader.next();
      if(!newsCatalog.contains(news)){
        news.postTag(unsort);

        String location = news.getLocation().substring(0, news.getLocation().indexOf("报")+1);
        locationTag = cLocation.getTag(location);
        news.postTag(locationTag);
        
        newsCatalog.add(news);
      }
    }

    return true;
  }
}
