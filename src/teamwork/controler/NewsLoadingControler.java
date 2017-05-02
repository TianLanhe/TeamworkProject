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
    
    //���δ�����ǩ
    NewsClass c = classCatalog.get("�Ƿ����");
    if(c==null){
      NewsClass newClass = new NewsClass("�Ƿ����");
      newClass.addTag(new Tag("�ѷ���"));
      newClass.addTag(new Tag("δ����"));
      c = newClass;
      classCatalog.add(c);
    }
    
    //�����������ǩ
    NewsClass cLocation = classCatalog.get("��������");
    if(cLocation==null){
      NewsClass newClass = new NewsClass("��������");
      newClass.addTag(new Tag("�����ձ�"));
      newClass.addTag(new Tag("�Ĵ��ձ�"));
      newClass.addTag(new Tag("�Ϸ����б�"));
      cLocation = newClass;
      classCatalog.add(cLocation);
    }

    News news;
    Tag unsort = c.getTag("δ����");
    Tag locationTag;
    while (newsLoader.hasNext()) {
      news = newsLoader.next();
      if(!newsCatalog.contains(news)){
        news.postTag(unsort);

        String location = news.getLocation().substring(0, news.getLocation().indexOf("��")+1);
        locationTag = cLocation.getTag(location);
        news.postTag(locationTag);
        
        newsCatalog.add(news);
      }
    }

    return true;
  }
}
