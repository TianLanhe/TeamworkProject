package teamwork.transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionSource {
  private List<Transaction> transactions;

  public TransactionSource() {
    this.transactions = new ArrayList<Transaction>();
  }

  public void add(Transaction t) {
    transactions.add(t);
  }

  public void run() {
    while (!transactions.isEmpty()) {
      Transaction t = transactions.get(0);
      transactions.remove(0);
      t.execute();
    }
  }
}
