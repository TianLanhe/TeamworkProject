package teamwork.listener;

import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import teamwork.model.controler.LoadControler;
import teamwork.model.viewmodel.NewsTreeModel;
import teamwork.r.R;

public class LoadListener extends IOListener {

  private JTree tagsTree;
  private JFileChooser jfc = new JFileChooser();
  
  public LoadListener() {
    tagsTree = (JTree) R.getInstance().getObject("tagsTree");
  }

  @Override
<<<<<<< HEAD
  public void actionPerformed(ActionEvent e) {
    // �����ļ�ѡ����
    
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
=======
  protected int showDialog() {
    return jfc.showDialog(null, "��ȡ");
  }

  @Override
  protected void saveOrLoad() {
    File file = jfc.getSelectedFile();
    if (!file.exists()) {
      JOptionPane.showMessageDialog(null, "�ļ������ڣ���ȡʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
    } else {
      LoadControler loadControler = new LoadControler();

      String pwdCheck = new String(pw.getPassword());
      String pwd = loadControler.loadPassword(file);

      // ���벻��
      if (!pwd.equals(pwdCheck)) {
        JOptionPane.showMessageDialog(null, "��������������ļ����벻��,��ȡʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
        return;
>>>>>>> 893a680e9d0e9e6f1755f65c6d35201d68af7d5e
      }

      if (!loadControler.load(file)) {
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
