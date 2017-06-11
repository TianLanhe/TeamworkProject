package teamwork.model.controler;

import teamwork.transaction.factory.LoadTransactionFactory;
import teamwork.transaction.factory.TransactionFactory;

public class LoadControler extends InputControler {

  @Override
  protected String getRootName() {
    return "SaveTransactions";
  }

  @Override
  protected TransactionFactory getTransactionFactory() {
    return new LoadTransactionFactory();
  }
}
