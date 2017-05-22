package teamwork.model.controler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import teamwork.model.BinCatalog;
import teamwork.model.ClassCatalog;
import teamwork.model.NewsCatalog;

public class SaveControler {
  
  public boolean save(String fileName) {
    return save(new File(fileName));
  }

  private boolean save(File file) {
    try {
      // 若文件不存在，则新建该文件
      if (!file.exists()) file.createNewFile();

      ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(file));

      objOut.writeObject(NewsCatalog.getInstance().getNewsList());
      objOut.writeObject(ClassCatalog.getInstance().getClassList());
      objOut.writeObject(ClassCatalog.getInstance().getTagToClassMap());
      objOut.writeObject(BinCatalog.getInstance().getNewsList());

      objOut.close();
    } catch (FileNotFoundException e) {
      return false;
    } catch (IOException e) {
      return false;
    }
    return false;
  }
}
