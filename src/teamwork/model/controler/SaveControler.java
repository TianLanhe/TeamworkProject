package teamwork.model.controler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import teamwork.model.BinCatalog;
import teamwork.model.ClassCatalog;
import teamwork.model.News;
import teamwork.model.NewsCatalog;
import teamwork.model.NewsClass;
import teamwork.model.Tag;
import teamwork.transaction.SaveAddNewsClassTransaction;
import teamwork.transaction.SaveAddNewsTransaction;
import teamwork.transaction.SaveAddTagTransaction;
import teamwork.transaction.SaveDeleteNewsTransaction;
import teamwork.transaction.SavePostTagTransaction;
import teamwork.transaction.SaveTagRelationTransaction;
import teamwork.transaction.TransactionSource;
import teamwork.util.XMLCreator;

public class SaveControler {

  public boolean save(String fileName) {
    return save(new File(fileName));
  }

  public boolean save(File file) {
    try {
      // 若文件不存在，则新建该文件
      if (!file.exists()) file.createNewFile();

      XMLCreator creator = new XMLCreator("Transactions");
      TransactionSource source = new TransactionSource();

      List<SaveTagRelationTransaction> transactions = new ArrayList<SaveTagRelationTransaction>();
      for (NewsClass c : ClassCatalog.getInstance().getClassList()) {
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

      for (News news : NewsCatalog.getInstance().getNewsList()) {
        source.add(new SaveAddNewsTransaction(creator, news));
        for (Tag tag : news.getTags()) {
          source.add(new SavePostTagTransaction(creator, news, tag));
        }
      }

      for (News news : BinCatalog.getInstance().getNewsList()) {
        source.add(new SaveDeleteNewsTransaction(creator, news));
      }

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
