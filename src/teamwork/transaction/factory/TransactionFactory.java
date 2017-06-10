package teamwork.transaction.factory;

import org.w3c.dom.Node;

import teamwork.transaction.Transaction;

public abstract class TransactionFactory {

  public abstract Transaction create(Node node);

}
