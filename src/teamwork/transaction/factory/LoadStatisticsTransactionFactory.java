package teamwork.transaction.factory;

import org.w3c.dom.Node;

import teamwork.transaction.LoadAddNewsClassTransaction;
import teamwork.transaction.LoadAddNewsTransaction;
import teamwork.transaction.LoadAddTagTransaction;
import teamwork.transaction.LoadDeleteNewsTransaction;
import teamwork.transaction.LoadPostTagTransaction;
import teamwork.transaction.LoadTagRelationTransaction;
import teamwork.transaction.Transaction;

public class LoadStatisticsTransactionFactory extends TransactionFactory {

  @Override
  public Transaction create(Node node) {
    String command = node.getAttributes().getNamedItem("name").getNodeValue();
    if (command.equals(Transaction.ADD_NEWS)) {
      return new LoadAddNewsTransaction(node, "statisticsCatalog", "statisticsCatalog");
    } else if (command.equals(Transaction.ADD_NEWS_CLASS)) {
      return new LoadAddNewsClassTransaction(node, "statisticsCatalog", "statisticsCatalog");
    } else if (command.equals(Transaction.ADD_TAG)) {
      return new LoadAddTagTransaction(node, "statisticsCatalog", "statisticsCatalog");
    } else if (command.equals(Transaction.DELETE_NEWS)) {
      return new LoadDeleteNewsTransaction(node, "statisticsCatalog", "statisticsCatalog");
    } else if (command.equals(Transaction.POST_TAG)) {
      return new LoadPostTagTransaction(node, "statisticsCatalog", "statisticsCatalog");
    } else if (command.equals(Transaction.ADD_RELATION)) {
      return new LoadTagRelationTransaction(node, "statisticsCatalog", "statisticsCatalog");
    } else {
      return null;
    }
  }

}
