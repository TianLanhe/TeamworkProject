package teamwork.model;

import java.util.List;

import javax.swing.AbstractListModel;

public class NewsListModel extends AbstractListModel<News> {
  private static final long serialVersionUID = 1L;

  private List<News> catalog;

  public NewsListModel() {
    catalog = NewsCatalog.getInstance().getNewsList();
  }
  
  public void notifyDataChanged(){
    fireContentsChanged(this, 0, getSize());
  }

  @Override
  public News getElementAt(int index) {
    return catalog.get(index);
  }

  @Override
  public int getSize() {
    return catalog.size();
  }

}
