package teamwork.listener;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import teamwork.model.News;
import teamwork.model.Tag;
import teamwork.model.viewmodel.NewsListModel;
import teamwork.model.viewmodel.NewsTreeModel;
import teamwork.r.R;

@SuppressWarnings("rawtypes")
public class NewsTreeSelectionListener implements TreeSelectionListener {

  private JLabel numLabel;
  private JLabel tagLabel;
  private JList newsList;
  private JTree tagsTree;

  private List<News> list;
  private Tag[] tags;

  public NewsTreeSelectionListener() {
    R r = R.getInstance();
    newsList = (JList) r.getObject("newsList");
    tagLabel = (JLabel) r.getObject("tagLabel");
    numLabel = (JLabel) r.getObject("numLabel");
  }

  @Override
  public void valueChanged(TreeSelectionEvent arg0) {
    tagsTree = (JTree) arg0.getSource();
    if (!tagsTree.isSelectionEmpty()) {
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

  private void getNewsList() {
    NewsTreeModel model = (NewsTreeModel) tagsTree.getModel();
    DefaultMutableTreeNode node =
        (DefaultMutableTreeNode) tagsTree.getSelectionPath().getLastPathComponent();
    tags = model.getTagsFromRoot(node);

    list = new ArrayList<News>(tags[0].getNewsList());
    for (int i = 1; i < tags.length; ++i) {
      list.retainAll(tags[i].getNewsList());
    }
  }

  private void displayNumLabel() {
    String msg = "一共有 " + list.size() + " 条新闻";
    numLabel.setText(msg);
  }

  private void displayTagLabel() {
    String str = "";
    for (Tag t : tags) {
      str += t.getName() + "  ";
    }
    tagLabel.setText(str);
  }

  @SuppressWarnings("unchecked")
  private void displayList() {
    NewsListModel<News> model = (NewsListModel<News>) newsList.getModel();
    model.setListData(list);
  }

}
