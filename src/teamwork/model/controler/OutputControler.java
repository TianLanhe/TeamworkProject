package teamwork.model.controler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import teamwork.model.ClassCatalog;
import teamwork.model.News;
import teamwork.model.NewsCatalog;
import teamwork.model.NewsClass;
import teamwork.model.Tag;
import teamwork.transaction.SaveAddNewsClassTransaction;
import teamwork.transaction.SaveAddNewsTransaction;
import teamwork.transaction.SaveAddTagTransaction;
import teamwork.transaction.SavePostTagTransaction;
import teamwork.transaction.SaveTagRelationTransaction;
import teamwork.transaction.TransactionSource;
import teamwork.util.XMLCreator;

public abstract class OutputControler {

  protected TransactionSource source;
  protected XMLCreator creator;
  private ClassCatalog classCatalog;
  private NewsCatalog newsCatalog;

  public OutputControler() {
    source = new TransactionSource();
    creator = createXMLCreator();
    classCatalog = getClassCatalog();
    newsCatalog = getNewsCatalog();
  }

  protected abstract ClassCatalog getClassCatalog();

  protected abstract XMLCreator createXMLCreator();

  protected abstract NewsCatalog getNewsCatalog();
  
  protected abstract void dealBinCatalog();

  public boolean save(String fileName) {
    return save(new File(fileName));
  }

  public boolean save(File file) {
    try {
      // 若文件不存在，则新建该文件
      if (!file.exists()) file.createNewFile();

      List<SaveTagRelationTransaction> transactions = new ArrayList<SaveTagRelationTransaction>();
      for (NewsClass c : classCatalog.getClassList()) {
        source.add(new SaveAddNewsClassTransaction(creator, c));

        for (Tag tag : c.getTagsList()) {
          source.add(new SaveAddTagTransaction(creator, tag));
          if (tag.hasNextClass()) {
            transactions.add(new SaveTagRelationTransaction(creator, tag));
          }
        }
      }
      for (SaveTagRelationTransaction transaction : transactions) {
        source.add(transaction);
      }

      for (News news : newsCatalog.getNewsList()) {
        source.add(new SaveAddNewsTransaction(creator, news));
        for (Tag tag : news.getTags()) {
          source.add(new SavePostTagTransaction(creator, news, tag));
        }
      }
      
      dealBinCatalog();

      source.run();
      creator.output(file);
    } catch (FileNotFoundException e) {
      return false;
    } catch (IOException e) {
      return false;
    }
    return true;
  }
}
