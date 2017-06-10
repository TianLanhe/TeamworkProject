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
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);// 只能选择文件
    jfc.setMultiSelectionEnabled(true);// 允许多选
    FileNameExtensionFilter filter = new FileNameExtensionFilter("统计文件(*.sfs)", "sfs");
    jfc.setFileFilter(filter);

    int state = jfc.showDialog(null, "导入");
    if (state == JFileChooser.APPROVE_OPTION) {
      File file = jfc.getSelectedFile();
      if (!file.exists()) {
        JOptionPane.showMessageDialog(null, "文件不存在，读取失败！", "错误", JOptionPane.ERROR_MESSAGE);
      } 
    }else{
      return;
    }
  }

}
