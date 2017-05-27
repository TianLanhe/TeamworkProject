package teamwork.model.controler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

public class SaveControler {

  public boolean save(String fileName) {
    return save(new File(fileName));
  }

  public boolean save(File file) {
    try {
      // 若文件不存在，则新建该文件
      if (!file.exists()) file.createNewFile();

      PrintWriter printer = new PrintWriter(new FileWriter(file));
      TransactionSource source = new TransactionSource();

      List<SaveTagRelationTransaction> transactions = new ArrayList<SaveTagRelationTransaction>();
      for (NewsClass c : ClassCatalog.getInstance().getClassList()) {
        source.add(new SaveAddNewsClassTransaction(printer, c));

        for (Tag tag : c.getTagsList()) {
          source.add(new SaveAddTagTransaction(printer, tag));
          if (tag.hasNextClass()) {
            transactions.add(new SaveTagRelationTransaction(printer, tag));
          }
        }
      }
      for (SaveTagRelationTransaction transaction : transactions) {
        source.add(transaction);
      }

      for (News news : NewsCatalog.getInstance().getNewsList()) {
        source.add(new SaveAddNewsTransaction(printer, news));
        for (Tag tag : news.getTags()) {
          source.add(new SavePostTagTransaction(printer, news, tag));
        }
      }

      for (News news : BinCatalog.getInstance().getNewsList()) {
        source.add(new SaveDeleteNewsTransaction(printer, news));
      }

      source.run();
      printer.close();
    } catch (FileNotFoundException e) {
      return false;
    } catch (IOException e) {
      return false;
    }
    return true;
  }
}
