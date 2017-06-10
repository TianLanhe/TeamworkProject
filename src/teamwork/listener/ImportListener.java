package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import teamwork.model.viewmodel.NewsTreeModel;

public class ImportListener implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
    JFileChooser jfc = new JFileChooser();
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);// ֻ��ѡ���ļ�
    jfc.setMultiSelectionEnabled(true);// �����ѡ
    FileNameExtensionFilter filter = new FileNameExtensionFilter("ͳ���ļ�(*.sfs)", "sfs");
    jfc.setFileFilter(filter);

    int state = jfc.showDialog(null, "����");
    if (state == JFileChooser.APPROVE_OPTION) {
      File file = jfc.getSelectedFile();
      if (!file.exists()) {
        JOptionPane.showMessageDialog(null, "�ļ������ڣ���ȡʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
      } 
    }else{
      return;
    }
  }

}
