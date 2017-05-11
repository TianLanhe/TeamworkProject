package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import teamwork.model.ClassCatalog;
import teamwork.model.News;
import teamwork.model.NewsListModel;
import teamwork.model.NewsTreeModel;
import teamwork.model.Tag;
import teamwork.r.R;
import teamwork.window.NewsTextWindow;

public class TearTagListener implements ActionListener {

  private JTree tagsTree;
  private JList<Tag> tagsList;

  @SuppressWarnings("unchecked")
  public TearTagListener() {
    R r = R.getInstance();
    tagsTree = (JTree) r.getObject("tagsTree");
    tagsList = (JList<Tag>) r.getObject("tagsList");
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    if (tagsTree.isSelectionEmpty()) {
      JOptionPane.showMessageDialog(null, "��ѡ��һ�����ձ�ǩ ��", "˺��ǩ", JOptionPane.INFORMATION_MESSAGE);
    } else {
      DefaultMutableTreeNode node =
          (DefaultMutableTreeNode) tagsTree.getSelectionPath().getLastPathComponent();
      if (!node.isLeaf()) {
        JOptionPane.showMessageDialog(null, "��ѡ��һ�����ձ�ǩ ��", "˺��ǩ", JOptionPane.INFORMATION_MESSAGE);
      } else {
        News news = ((NewsTextWindow) R.getInstance().getObject("NewsTextWindow")).getNews();
        NewsTreeModel model = (NewsTreeModel) tagsTree.getModel();
        Tag[] tags = model.getTagsFromRoot(node);

        if (tags[tags.length - 1].getParent() == ClassCatalog.getInstance().get("�Ƿ����")
            || tags[tags.length - 1].getParent() == ClassCatalog.getInstance().get("��������")) {
          JOptionPane.showMessageDialog(null, "�Բ��𣬸ñ�ǩ��ϵͳĬ�����ã��û��޷��ı� ��", "˺��ǩ",
              JOptionPane.INFORMATION_MESSAGE);
          return;
        }

        // Ҫ�Ӻ���ǰ�Ƴ������ܻ��������һ����ǩ��δ���ϵ����������ǰ���������ǰ����Ѿ��Ƴ��˲ŷ��ֺ������Ĳ���
        for (int i = tags.length - 1; i >= 0; --i) {
          if (!news.tearTag(tags[i])) {
            JOptionPane.showMessageDialog(null, "��û������ " + tags[i].getName() + " ��ǩ��", "˺��ǩ",
                JOptionPane.WARNING_MESSAGE);
            break;
          }
        }
        ((NewsListModel<Tag>) tagsList.getModel()).setListData(news.getTagsList());
      }
    }
  }
}
