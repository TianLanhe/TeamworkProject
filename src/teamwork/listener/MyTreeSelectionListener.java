package teamwork.listener;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import teamwork.model.News;
import teamwork.model.Tag;
import teamwork.model.viewmodel.NewsTreeModel;

public abstract class MyTreeSelectionListener implements TreeSelectionListener {

  protected JLabel numLabel;
  protected JLabel tagLabel;

  protected JTree tagsTree;

  protected List<News> list;
  protected Tag[] tags;

  @Override
  public void valueChanged(TreeSelectionEvent arg0) {
    tagsTree = (JTree) arg0.getSource();
    if (!tagsTree.isSelectionEmpty()) {
      showDetail();
    }
  }

  protected abstract void showDetail();

  // �õ�����������б�
  protected void getNewsList() {
    NewsTreeModel model = (NewsTreeModel) tagsTree.getModel();
    DefaultMutableTreeNode node =
        (DefaultMutableTreeNode) tagsTree.getSelectionPath().getLastPathComponent();
    tags = model.getTagsFromRoot(node);

    list = new ArrayList<News>(tags[0].getNewsList());
    for (int i = 1; i < tags.length; ++i) {
      list.retainAll(tags[i].getNewsList());
    }
  }

  //��ӡ��������
  protected void displayNumLabel() {
    String msg = "һ���� " + list.size() + " ������";
    numLabel.setText(msg);
  }

  //��ӡ��ѡ��ǩ�б�
  protected void displayTagLabel() {
    String str = "";
    for (Tag t : tags) {
      str += t.getName() + "  ";
    }
    tagLabel.setText(str);
  }

}
