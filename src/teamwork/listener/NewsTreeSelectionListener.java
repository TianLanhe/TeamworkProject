package teamwork.listener;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import teamwork.model.News;
import teamwork.model.NewsListModel;
import teamwork.model.NewsTreeModel;
import teamwork.model.Tag;
import teamwork.r.R;

@SuppressWarnings("rawtypes")
public class NewsTreeSelectionListener implements TreeSelectionListener {

  private JLabel numLabel;
  private JLabel tagLabel;
  private JList newsList;
  private JTree tagsTree;

  private Tag tag;

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
        tag = (Tag) node.getUserObject();

        displayList();
        displayNumLabel();
        displayTagLabel();
      }
    }
  }

  private void displayNumLabel() {
    String msg = "一共有 " + tag.getNewsList().size() + " 条新闻";
    numLabel.setText(msg);
  }

  private void displayTagLabel() {
    NewsTreeModel model = (NewsTreeModel) tagsTree.getModel();
    TreeNode[] nodes = model.getPathToRoot(tag.getName());
    String str = "";
    for (TreeNode n : nodes) {
      DefaultMutableTreeNode dn = (DefaultMutableTreeNode) n;
      if (dn.getUserObject() instanceof Tag) {
        str += ((Tag) dn.getUserObject()).getName() + "  ";
      }
    }
    tagLabel.setText(str);
  }

  @SuppressWarnings("unchecked")
  private void displayList() {
    NewsListModel<News> model = (NewsListModel<News>) newsList.getModel();
    model.setListData(tag.getNewsList());
  }

}
