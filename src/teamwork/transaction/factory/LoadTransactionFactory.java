package teamwork.transaction.factory;

import org.w3c.dom.Node;

import teamwork.transaction.LoadAddNewsClassTransaction;
import teamwork.transaction.LoadAddNewsTransaction;
import teamwork.transaction.LoadAddTagTransaction;
import teamwork.transaction.LoadDeleteNewsTransaction;
import teamwork.transaction.LoadPostTagTransaction;
import teamwork.transaction.LoadTagRelationTransaction;
import teamwork.transaction.Transaction;

public class LoadTransactionFactory {

  public Transaction create(Node node) {
    String command = node.getAttributes().getNamedItem("name").getNodeValue();
    if (command.equals(Transaction.ADD_NEWS)) {
      return new LoadAddNewsTransaction(node);
    } else if (command.equals(Transaction.ADD_NEWS_CLASS)) {
      return new LoadAddNewsClassTransaction(node);
    } else if (command.equals(Transaction.ADD_TAG)) {
      return new LoadAddTagTransaction(node);
    } else if (command.equals(Transaction.DELETE_NEWS)) {
      return new LoadDeleteNewsTransaction(node);
    } else if (command.equals(Transaction.POST_TAG)) {
      return new LoadPostTagTransaction(node);
    } else if (command.equals(Transaction.ADD_RELATION)) {
      return new LoadTagRelationTransaction(node);
    } else {
      return null;
    }
  }

}
