package teamwork.model.controler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Map;

import teamwork.model.BinCatalog;
import teamwork.model.ClassCatalog;
import teamwork.model.News;
import teamwork.model.NewsCatalog;
import teamwork.model.NewsClass;
import teamwork.model.Tag;

public class LoadControler {
  public boolean load(String fileName) {
    return load(new File(fileName));
  }

  @SuppressWarnings("unchecked")
  private boolean load(File file) {
    try {
      // 若文件不存在，则返回false
      if (!file.exists()) return false;

      ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(file));

      NewsCatalog.getInstance().setNewsList((List<News>) objIn.readObject());
      ClassCatalog.getInstance().setClassList((List<NewsClass>) objIn.readObject());
      ClassCatalog.getInstance().setTagToClassMap((Map<Tag, NewsClass>) objIn.readObject());
      BinCatalog.getInstance().setNewsList((List<News>) objIn.readObject());

      objIn.close();
    } catch (FileNotFoundException e) {
      return false;
    } catch (IOException e) {
      return false;
    } catch (ClassNotFoundException e) {
      return false;
    }
    return false;
  }
}
