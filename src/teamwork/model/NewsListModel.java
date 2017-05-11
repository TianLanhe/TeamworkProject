package teamwork.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

public class NewsListModel<T> extends AbstractListModel<T> {
  private static final long serialVersionUID = 1L;

  private List<T> list;

  public NewsListModel() {
    this(new ArrayList<T>());
  }

  public NewsListModel(List<T> newsList) {
    list = newsList;
  }

  public void setListData(List<T> newsList) {
    list = newsList;
    notifyDataChanged();
  }
  
  public List<T> getListData(){
    return list;
  }

  public void notifyDataChanged() {
    fireContentsChanged(this, 0, getSize());
  }

  @Override
  public T getElementAt(int index) {
    return list.get(index);
  }

  @Override
  public int getSize() {
    return list.size();
  }

}
