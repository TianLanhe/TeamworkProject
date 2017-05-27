package teamwork.transaction.factory;

import teamwork.transaction.LoadAddNewsClassTransaction;
import teamwork.transaction.LoadAddNewsTransaction;
import teamwork.transaction.LoadAddTagTransaction;
import teamwork.transaction.LoadDeleteNewsTransaction;
import teamwork.transaction.LoadPostTagTransaction;
import teamwork.transaction.LoadTagRelationTransaction;
import teamwork.transaction.Transaction;

public class LoadTransactionFactory {

  public Transaction create(String line) {
    String command = line.split(" ")[0];
    if (command.equals("addNews")) {
      return new LoadAddNewsTransaction(line);
    } else if (command.equals("addNewsClass")) {
      return new LoadAddNewsClassTransaction(line);
    } else if (command.equals("addTag")) {
      return new LoadAddTagTransaction(line);
    } else if (command.equals("deleteNews")) {
      return new LoadDeleteNewsTransaction(line);
    } else if (command.equals("postTag")) {
      return new LoadPostTagTransaction(line);
    } else if (command.equals("addRelation")) {
      return new LoadTagRelationTransaction(line);
    } else {
      return null;
    }
  }

}
