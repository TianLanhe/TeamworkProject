package teamwork.transaction.factory;

import org.w3c.dom.Node;

import teamwork.transaction.LoadAddNewsClassTransaction;
import teamwork.transaction.LoadAddNewsTransaction;
import teamwork.transaction.LoadAddTagTransaction;
import teamwork.transaction.LoadDeleteNewsTransaction;
import teamwork.transaction.LoadPostTagTransaction;
import teamwork.transaction.LoadTagRelationTransaction;
import teamwork.transaction.Transaction;

public class LoadTestTransactionFactory extends TransactionFactory {

  @Override
  public Transaction create(Node node) {
    String command = node.getAttributes().getNamedItem("name").getNodeValue();
    if (command.equals(Transaction.ADD_NEWS)) {
      return new LoadAddNewsTransaction(node, "testCatalog", "testCatalog");
    } else if (command.equals(Transaction.ADD_NEWS_CLASS)) {
      return new LoadAddNewsClassTransaction(node, "testCatalog", "testCatalog");
    } else if (command.equals(Transaction.ADD_TAG)) {
      return new LoadAddTagTransaction(node, "testCatalog", "testCatalog");
    } else if (command.equals(Transaction.DELETE_NEWS)) {
      return new LoadDeleteNewsTransaction(node, "testCatalog", "testCatalog");
    } else if (command.equals(Transaction.POST_TAG)) {
      return new LoadPostTagTransaction(node, "testCatalog", "testCatalog");
    } else if (command.equals(Transaction.ADD_RELATION)) {
      return new LoadTagRelationTransaction(node, "testCatalog", "testCatalog");
    } else {
      return null;
    }
  }

}
