package teamwork.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

public class NewsListModel extends AbstractListModel<News> {
  private static final long serialVersionUID = 1L;

  private List<News> list;

  public NewsListModel() {
    this(new ArrayList<News>());
  }

  public NewsListModel(List<News> newsList) {
    list = newsList;
  }

  public void setListData(List<News> newsList) {
    list = newsList;
    notifyDataChanged();
  }

  public void notifyDataChanged() {
    fireContentsChanged(this, 0, getSize());
  }

  @Override
  public News getElementAt(int index) {
    return list.get(index);
  }

  @Override
  public int getSize() {
    return list.size();
  }

}
