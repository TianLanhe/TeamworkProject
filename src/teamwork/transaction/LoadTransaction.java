package teamwork.transaction;

public abstract class LoadTransaction extends Transaction {

  private String command;

  public LoadTransaction(String str) {
    command = str;
  }

  @Override
  public void execute() {
    parseCommand(command);
  }

  protected abstract void parseCommand(String command);

}
