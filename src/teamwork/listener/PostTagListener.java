package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import teamwork.model.News;
import teamwork.model.Tag;
import teamwork.model.viewmodel.NewsTreeModel;
import teamwork.model.viewmodel.TagListModel;
import teamwork.r.R;
import teamwork.window.NewsTextWindow;

public class PostTagListener implements ActionListener {

  private JTree tagsTree;
  private JList<String> tagsList;

  @SuppressWarnings("unchecked")
  public PostTagListener() {
    R r = R.getInstance();
    tagsTree = (JTree) r.getObject("tagsTree");
    tagsList = (JList<String>) r.getObject("tagsList");
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    if (tagsTree.isSelectionEmpty()) {
      JOptionPane.showMessageDialog(null, "��ѡ��һ�����ձ�ǩ ��", "����ǩ", JOptionPane.INFORMATION_MESSAGE);
    } else {
      DefaultMutableTreeNode node =
          (DefaultMutableTreeNode) tagsTree.getSelectionPath().getLastPathComponent();
      if (!node.isLeaf()) {
        JOptionPane.showMessageDialog(null, "��ѡ��һ�����ձ�ǩ ��", "����ǩ", JOptionPane.INFORMATION_MESSAGE);
      } else {
        News news = ((NewsTextWindow) R.getInstance().getObject("NewsTextWindow")).getNews();
        NewsTreeModel model = (NewsTreeModel) tagsTree.getModel();
        Tag[] tags = model.getTagsFromRoot(node);

        if (tags[tags.length - 1].getParent().getName().equals("�Ƿ����")) {
          JOptionPane.showMessageDialog(null, "�Բ��𣬸ñ�ǩ��ϵͳĬ�����ã��û��޷��ı� ��", "����ǩ",
              JOptionPane.INFORMATION_MESSAGE);
          return;
        }

        boolean flag = false;
        for (Tag tag : tags) {
          if (news.postTag(tag)) {
            flag = true;
          }
        }

        // һ����ǩ��û����
        if (!flag) {
          String tagName = "";
          for (int i = 0; i < tags.length; ++i) {
            if (i != 0)
              tagName += "-" + tags[i].getName();
            else
              tagName += tags[i].getName();
          }
          JOptionPane.showMessageDialog(null, "�޷��ظ����� " + tagName + " ��ǩ��", "����ǩ",
              JOptionPane.WARNING_MESSAGE);
        }
        ((TagListModel) tagsList.getModel()).setListData(news.getTagsList());
      }
    }
  }

}
