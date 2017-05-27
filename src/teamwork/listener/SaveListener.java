package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import teamwork.model.controler.SaveControler;

public class SaveListener implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
    JFileChooser jfc = new JFileChooser();
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
    FileNameExtensionFilter filter = new FileNameExtensionFilter("进度文件(*.mmp)", "mmp");
    jfc.setFileFilter(filter);

    int state = jfc.showSaveDialog(null);
    if (state == JFileChooser.APPROVE_OPTION) {
      String name = jfc.getSelectedFile().getName();

      File file = new File(jfc.getCurrentDirectory(), name);
      if (file.exists()) {// 已存在文件
        int flag =
            JOptionPane.showConfirmDialog(null, file + "文件已经存在,是否覆盖?", "文件存在",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);// 显示一个对话框来实现是否覆盖源文件
        if (flag == JOptionPane.NO_OPTION) {
          return;
        }
      } else if (name.length() < 4 || name.indexOf(".mmp") != name.length() - 4) {
        file = new File(jfc.getCurrentDirectory(), name + ".mmp");
      }
      SaveControler saveControler = new SaveControler();
      if (!saveControler.save(file)) {
        JOptionPane.showMessageDialog(null, "保存失败！", "错误", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
}
