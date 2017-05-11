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
      JOptionPane.showMessageDialog(null, "请选择一个最终标签 ！", "撕标签", JOptionPane.INFORMATION_MESSAGE);
    } else {
      DefaultMutableTreeNode node =
          (DefaultMutableTreeNode) tagsTree.getSelectionPath().getLastPathComponent();
      if (!node.isLeaf()) {
        JOptionPane.showMessageDialog(null, "请选择一个最终标签 ！", "撕标签", JOptionPane.INFORMATION_MESSAGE);
      } else {
        News news = ((NewsTextWindow) R.getInstance().getObject("NewsTextWindow")).getNews();
        NewsTreeModel model = (NewsTreeModel) tagsTree.getModel();
        Tag[] tags = model.getTagsFromRoot(node);

        if (tags[tags.length - 1].getParent() == ClassCatalog.getInstance().get("是否分类")
            || tags[tags.length - 1].getParent() == ClassCatalog.getInstance().get("报道数量")) {
          JOptionPane.showMessageDialog(null, "对不起，该标签由系统默认设置，用户无法改变 ！", "撕标签",
              JOptionPane.INFORMATION_MESSAGE);
          return;
        }

        // 要从后往前移除，可能会遇到最后一个标签并未贴上的情况，若从前往后操作则前面的已经移除了才发现后面贴的不对
        for (int i = tags.length - 1; i >= 0; --i) {
          if (!news.tearTag(tags[i])) {
            JOptionPane.showMessageDialog(null, "您没有贴上 " + tags[i].getName() + " 标签！", "撕标签",
                JOptionPane.WARNING_MESSAGE);
            break;
          }
        }
        ((NewsListModel<Tag>) tagsList.getModel()).setListData(news.getTagsList());
      }
    }
  }
}
