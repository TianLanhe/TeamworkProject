package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import teamwork.model.controler.LoadControler;

public class DoTestListener implements ActionListener{
  
  private JFileChooser jfc = new JFileChooser();
  
  @Override
  public void actionPerformed(ActionEvent e) {

    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
    FileNameExtensionFilter filter = new FileNameExtensionFilter("测试文件(*.test)", "test");
    jfc.setFileFilter(filter);

    int state = jfc.showDialog(null, "读取");
    if (state == JFileChooser.APPROVE_OPTION) {
      
      File file = jfc.getSelectedFile();
      if (!file.exists()) {
        JOptionPane.showMessageDialog(null, "文件不存在，读取失败！", "错误", JOptionPane.ERROR_MESSAGE);
      } else {
        LoadControler loadControler = new LoadControler();
        if (!loadControler.load(file)) {
          JOptionPane.showMessageDialog(null, "读取失败！", "错误", JOptionPane.ERROR_MESSAGE);
        }
      }
    }
  }
}
