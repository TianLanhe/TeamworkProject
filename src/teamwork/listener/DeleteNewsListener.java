package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import teamwork.model.News;
import teamwork.model.NewsTreeModel;
import teamwork.r.R;

public class DeleteNewsListener implements ActionListener {

  private JList<News> newsList;
  private JTree tagsTree;

  @SuppressWarnings("unchecked")
  public DeleteNewsListener() {
    R r = R.getInstance();
    tagsTree = (JTree) r.getObject("tagsTree");
    newsList = (JList<News>) r.getObject("newsList");
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    List<News> newsSelected = newsList.getSelectedValuesList();
    if (newsSelected.size() == 0) {
      JOptionPane.showMessageDialog(null, "请用鼠标和Ctrl/Alt选择一条或多条新闻 ！", "删除新闻",
          JOptionPane.INFORMATION_MESSAGE);
    } else {
      for (News news : newsSelected)
        news.delete();

      // 刷新列表
      NewsTreeModel model = (NewsTreeModel) tagsTree.getModel();
      model.updateTree();
      TreeNode[] nodes = model.getPathToRoot("未分类");
      TreePath path = new TreePath(nodes);
      // 显示"未分类"标签下的新闻
      tagsTree.setSelectionPath(path);
    }
  }
}
