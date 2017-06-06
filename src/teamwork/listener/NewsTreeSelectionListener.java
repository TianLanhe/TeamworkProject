package teamwork.listener;


import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.tree.DefaultMutableTreeNode;

import teamwork.model.News;
import teamwork.model.Tag;
import teamwork.model.viewmodel.NewsListModel;
import teamwork.r.R;

@SuppressWarnings("rawtypes")
public class NewsTreeSelectionListener extends MyTreeSelectionListener {

  private JList newsList;

  public NewsTreeSelectionListener() {
    R r = R.getInstance();
    newsList = (JList) r.getObject("newsList");
    tagLabel = (JLabel) r.getObject("tagLabel");
    numLabel = (JLabel) r.getObject("numLabel");
  }

  @SuppressWarnings("unchecked")
  private void displayList() {
    NewsListModel<News> model = (NewsListModel<News>) newsList.getModel();
    model.setListData(list);
  }

  @Override
  protected void showDetail() {
    DefaultMutableTreeNode node =
        (DefaultMutableTreeNode) tagsTree.getSelectionPath().getLastPathComponent();
    if (node.getUserObject() instanceof Tag) {
      getNewsList();

      displayList();
      displayNumLabel();
      displayTagLabel();
    }
  }

}
