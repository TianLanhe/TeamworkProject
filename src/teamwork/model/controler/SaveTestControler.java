package teamwork.model.controler;


import teamwork.model.ClassCatalog;
import teamwork.model.NewsCatalog;
import teamwork.util.XMLCreator;

public class SaveTestControler extends OutputControler {

  @Override
  protected ClassCatalog getClassCatalog() {
    return ClassCatalog.getInstance("testCatalog");
  }

  @Override
  protected NewsCatalog getNewsCatalog() {
    return NewsCatalog.getInstance("testCatalog");
  }

  @Override
  protected XMLCreator createXMLCreator() {
    return new XMLCreator("TestTransactions");
  }

  @Override
  protected void dealBinCatalog() {}

}
