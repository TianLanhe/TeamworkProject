package teamwork.model;

import java.util.ArrayList;

public class RecycleBin {

    private static RecycleBin instance = null;
    
    private List<News> newsList;

    private RecycleBin() {
      newsList = new ArrayList<News>();
    }
     
    public static RecycleBin getInstance() {
      if (instance == null) {
        instance = new RecycleBin();
      }
      return instance;
    }

    public boolean add(News news) {
      newsList.add(news);
      return true;
    }
    
    public void remove(News news) {
      newsList.remove(news);
    }
}

