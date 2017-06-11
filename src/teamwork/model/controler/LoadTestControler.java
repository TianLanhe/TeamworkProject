package teamwork.model.controler;

import teamwork.transaction.factory.LoadTestTransactionFactory;
import teamwork.transaction.factory.TransactionFactory;

public class LoadTestControler extends InputControler {

  @Override
  protected String getRootName() {
    return "TestTransactions";
  }

  @Override
  protected TransactionFactory getTransactionFactory() {
    return new LoadTestTransactionFactory();
  }

}
