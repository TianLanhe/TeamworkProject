package teamwork.model.viewmodel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import teamwork.model.Tag;

public class TagListModel extends AbstractListModel<String> {

  private static final long serialVersionUID = 1L;

  private List<Tag> list;

  public TagListModel() {
    this(new ArrayList<Tag>());
  }

  public TagListModel(List<Tag> tagsList) {
    list = tagsList;
  }

  public void setListData(List<Tag> newsList) {
    list = newsList;
    notifyDataChanged();
  }

  public List<Tag> getListData() {
    return list;
  }

  public void notifyDataChanged() {
    fireContentsChanged(this, 0, getSize());
  }

  @Override
  public String getElementAt(int index) {
    return list.get(index).getParent().getName() + "-" + list.get(index).getName();
  }

  @Override
  public int getSize() {
    return list.size();
  }
}
