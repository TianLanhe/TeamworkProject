package teamwork.transaction;

import org.w3c.dom.Element;

import teamwork.util.XMLCreator;

public class SavePasswordTransaction extends SaveTransaction {

  private String pwd;

  public SavePasswordTransaction(XMLCreator creator, String password) {
    super(creator);
    pwd = password;
  }

  @Override
  protected Element createElement() {
    Element ele = creator.getNewElement("Password");
    ele.setAttribute("password", pwd);
    return ele;
  }

}
