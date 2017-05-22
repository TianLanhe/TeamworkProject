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
      JOptionPane.showMessageDialog(null, "��������Ctrl/Altѡ��һ����������� ��", "ɾ������",
          JOptionPane.INFORMATION_MESSAGE);
    } else {
      for (News news : newsSelected)
        news.delete();

      // ˢ���б�
      NewsTreeModel model = (NewsTreeModel) tagsTree.getModel();
      model.updateTree();
      TreeNode[] nodes = model.getPathToRoot("δ����");
      TreePath path = new TreePath(nodes);
      // ��ʾ"δ����"��ǩ�µ�����
      tagsTree.setSelectionPath(path);
    }
  }
}
