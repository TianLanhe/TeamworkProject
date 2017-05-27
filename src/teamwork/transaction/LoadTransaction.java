package teamwork.transaction;

import org.w3c.dom.Node;

public abstract class LoadTransaction extends Transaction {

  private Node node;

  public LoadTransaction(Node node) {
    this.node = node;
  }

  @Override
  public void execute() {
    parseNode(node);
  }

  protected abstract void parseNode(Node node);

}
