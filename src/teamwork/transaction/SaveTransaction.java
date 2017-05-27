package teamwork.transaction;

import org.w3c.dom.Element;

import teamwork.util.XMLCreator;

public abstract class SaveTransaction extends Transaction {
  protected XMLCreator creator;

  public SaveTransaction(XMLCreator c) {
    creator = c;
  }

  protected abstract Element createElement();

  @Override
  public void execute() {
    creator.appendChild(createElement());
  }
}
