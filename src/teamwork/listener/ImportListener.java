package teamwork.listener;

import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import teamwork.model.controler.ImportControler;
import teamwork.model.viewmodel.NewsTreeModel;
import teamwork.r.R;

public class ImportListener extends IOListener {

  private JTree tagsTree;

  public ImportListener() {
    tagsTree = (JTree) R.getInstance().getObject("statisticsTagsTree");
  }

  @Override
  protected int showDialog() {
    return jfc.showDialog(null, "����");
  }

  @Override
  protected void saveOrLoad() {
    File file = jfc.getSelectedFile();
    if (!file.exists()) {
      JOptionPane.showMessageDialog(null, "�ļ������ڣ���ȡʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
    } else {
      ImportControler importControler = new ImportControler();

      String pwdCheck = new String(pw.getPassword());
      String pwd = importControler.loadPassword(file);

      // ���벻��
      if (!pwd.equals(pwdCheck)) {
        JOptionPane.showMessageDialog(null, "��������������ļ����벻��,��ȡʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
        return;
      }

      if (!importControler.load(file)) {
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
