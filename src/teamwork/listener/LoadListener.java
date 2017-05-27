package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import teamwork.model.controler.LoadControler;
import teamwork.model.viewmodel.NewsTreeModel;
import teamwork.r.R;

public class LoadListener implements ActionListener {

  private JTree tagsTree;

  public LoadListener() {
    tagsTree = (JTree) R.getInstance().getObject("tagsTree");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // �����ļ�ѡ����
    JFileChooser jfc = new JFileChooser();
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
    FileNameExtensionFilter filter = new FileNameExtensionFilter("�����ļ�(*.mmp)", "mmp");
    jfc.setFileFilter(filter);

    int state = jfc.showDialog(null, "��ȡ");
    if (state == JFileChooser.APPROVE_OPTION) {
      File file = jfc.getSelectedFile();
      if (!file.exists()) {
        JOptionPane.showMessageDialog(null, "�ļ������ڣ���ȡʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
      } else {
        LoadControler loadControler = new LoadControler();
        if (!loadControler.load(file)) {
          JOptionPane.showMessageDialog(null, "��ȡʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
        }

        // �������ṹ
        NewsTreeModel model = (NewsTreeModel) tagsTree.getModel();
        model.updateTree();

        TreeNode[] nodes = model.getPathToRoot("δ����");
        TreePath path = new TreePath(nodes);
        // ��ʾ"δ����"��ǩ�µ�����
        tagsTree.setSelectionPath(path);
      }
    }
  }
}
