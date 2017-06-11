package teamwork.model.controler;

import teamwork.model.ClassCatalog;
import teamwork.model.NewsCatalog;
import teamwork.transaction.SavePasswordTransaction;
import teamwork.util.XMLCreator;

public class ExportControler extends OutputControler {

  public void addPassword(char[] pwd) {
    addPassword(new String(pwd));
  }

  public void addPassword(String password) {
    source.add(new SavePasswordTransaction(creator, password));
  }

  @Override
  protected ClassCatalog getClassCatalog() {
    return ClassCatalog.getInstance();
  }

  @Override
  protected XMLCreator createXMLCreator() {
    return new XMLCreator("StatisticsTransactions");
  }

  @Override
  protected NewsCatalog getNewsCatalog() {
    return NewsCatalog.getInstance();
  }

  @Override
  protected void dealBinCatalog() {
  }
}
