package teamwork.model.controler;

import teamwork.model.BinCatalog;
import teamwork.model.ClassCatalog;
import teamwork.model.News;
import teamwork.model.NewsCatalog;
import teamwork.transaction.SaveDeleteNewsTransaction;
import teamwork.util.XMLCreator;

public class SaveControler extends OutputControler {

  @Override
  protected void dealBinCatalog() {
    for (News news : BinCatalog.getInstance().getNewsList()) {
      source.add(new SaveDeleteNewsTransaction(creator, news));
    }
  }

  @Override
  protected ClassCatalog getClassCatalog() {
    return ClassCatalog.getInstance();
  }

  @Override
  protected XMLCreator createXMLCreator() {
    return new XMLCreator("SaveTransactions");
  }

  @Override
  protected NewsCatalog getNewsCatalog() {
    return NewsCatalog.getInstance();
  }

}
