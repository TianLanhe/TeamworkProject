package teamwork.transaction;

import org.w3c.dom.Node;

import teamwork.model.ClassCatalog;
import teamwork.model.NewsCatalog;

public abstract class LoadTransaction extends Transaction {

  private Node node;
  private ClassCatalog classCatalog;
  private NewsCatalog newsCatalog;

  public LoadTransaction(Node node) {
    this.node = node;
    classCatalog = ClassCatalog.getInstance();
    newsCatalog = NewsCatalog.getInstance();
  }
  
  public LoadTransaction(Node node,String newsTagName,String classTagName){
    this.node = node;
    classCatalog = ClassCatalog.getInstance(newsTagName);
    newsCatalog = NewsCatalog.getInstance(classTagName);
  }

  @Override
  public void execute() {
    parseNode(node);
  }

  protected abstract void parseNode(Node node);
  
  protected NewsCatalog getNewsCatalog(){
    return newsCatalog;
  }
  
  protected ClassCatalog getClassCatalog(){
    return classCatalog;
  }

}
