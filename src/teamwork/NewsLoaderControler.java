public class NewsLoaderControler {
  private NewsLoader newsLoader;
  private NewsCatalog newsCatalog;
  private News news;

  public NewsLoaderControler(NewsLoader newsLoader,NewsCatalog newsCatalog) {
  this.newsLoder=newsLoader;
  this.newsCatalog=newsCatalog;
  }
  
  public boolean loadData(String file){
    
    newsLoder.loadFile(file);
    while(newsLoder.hasNext()!=null){
      news=newsLoder.next();
      newsCatalog.addNews(news);  
    }
    
    return true;
  }
}

