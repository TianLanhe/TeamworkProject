package teamwork.transaction;

import java.io.PrintWriter;

public abstract class SaveTransaction extends Transaction {
  private PrintWriter printer;

  public SaveTransaction(PrintWriter p) {
    printer = p;
  }

  protected abstract String getCommandString();

  @Override
  public void execute() {
    printer.println(getCommandString());
  }
}
