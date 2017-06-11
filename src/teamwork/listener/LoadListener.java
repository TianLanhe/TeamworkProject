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
    R r = R.getInstance();
    tagsTree = (JTree) r.getObject("tagsTree");
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    JFileChooser jfc = new JFileChooser();
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);// ֻ��ѡ���ļ�
    FileNameExtensionFilter filter = new FileNameExtensionFilter("�����ļ�(*.mmp)", "mmp");
    jfc.setFileFilter(filter);

    int state = jfc.showDialog(null, "��ȡ");
    if (state == JFileChooser.APPROVE_OPTION) {
      File file = jfc.getSelectedFile();

      // ���ѡ���ļ��Ƿ����
      if (!file.exists()) {
        JOptionPane.showMessageDialog(null, "�ļ������ڣ���ȡʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
      } else {
        // // ���ClassCatalog��NewsCatalog������
        // ClassCatalog.getInstance().clear();
        // NewsCatalog.getInstance().clear();

        if (!new LoadControler().load(file)) {
          JOptionPane.showMessageDialog(null, "��ȡʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
          return;
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
