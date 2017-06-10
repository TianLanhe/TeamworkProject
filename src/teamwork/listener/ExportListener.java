package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ExportListener implements ActionListener{

  @Override
  public void actionPerformed(ActionEvent e) {
    JFileChooser jfc = new JFileChooser();
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);//只能选择文件
    FileNameExtensionFilter filter = new FileNameExtensionFilter("统计文件(*.sfs)", "sfs");
    jfc.setDialogTitle("导出");
    jfc.setFileFilter(filter);

    int state = jfc.showSaveDialog(null);
    if (state == JFileChooser.APPROVE_OPTION) {
      String name = jfc.getSelectedFile().getName();
      
      if (name.length() < 4 || name.indexOf(".sfs") != name.length() - 4) {
        name += ".sfs";
      }

      File file = new File(jfc.getCurrentDirectory(), name);
      if (file.exists()) {// 已存在文件
        int flag =
            JOptionPane.showConfirmDialog(null, file + "文件已经存在,是否覆盖?", "文件存在",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);// 显示一个对话框来实现是否覆盖源文件
        if (flag == JOptionPane.NO_OPTION) {
          return;
        }

      }
     
    }
    
  }

}
