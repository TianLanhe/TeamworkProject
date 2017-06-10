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

public class SaveTestControler {

  public boolean save(String fileName) {
    return save(new File(fileName));
  }

  public boolean save(File file) {
    try {
      // ���ļ������ڣ����½����ļ�
      if (!file.exists()) file.createNewFile();

      TransactionSource source = new TransactionSource();
      XMLCreator creator = new XMLCreator("TestTransactions");

      List<SaveTagRelationTransaction> transactions = new ArrayList<SaveTagRelationTransaction>();
      for (NewsClass c : ClassCatalog.getInstance("testCatalog").getClassList()) {
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

      for (News news : NewsCatalog.getInstance("testCatalog").getNewsList()) {
        source.add(new SaveAddNewsTransaction(creator, news));
        for (Tag tag : news.getTags()) {
          source.add(new SavePostTagTransaction(creator, news, tag));
        }
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
