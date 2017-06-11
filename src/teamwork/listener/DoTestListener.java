package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import teamwork.model.controler.DoTest;

public class DoTestListener implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
    JFileChooser jfc = new JFileChooser();
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);// 只能选择文件
    jfc.setMultiSelectionEnabled(true);// 允许多选
    FileNameExtensionFilter filter = new FileNameExtensionFilter("测试文件(*.test)", "test");
    jfc.setFileFilter(filter);

    int state = jfc.showDialog(null, "测试");
    if (state == JFileChooser.APPROVE_OPTION) {
      File[] files = jfc.getSelectedFiles();
      if (files.length != 2) {
        JOptionPane
            .showMessageDialog(null, "请选择两个测试文件进行测试！", "提示", JOptionPane.INFORMATION_MESSAGE);
      } else {
        // 检测所有选中文件是否存在
        for (File file : files) {
          if (!file.exists()) {
            JOptionPane.showMessageDialog(null, "文件不存在，读取失败！", "错误", JOptionPane.ERROR_MESSAGE);
            return;
          }
        }

        double percent = new DoTest().test(files[0], files[1]);
        if (Math.abs(percent + 1) < 1e-5) {
          JOptionPane.showMessageDialog(null, "文件格式错误或文件来自不同测试集，测试失败！", "错误",
              JOptionPane.ERROR_MESSAGE);
        } else {
          JOptionPane.showMessageDialog(null, "测试文件一致性比对： " + (percent * 100) + "%", "测试结果",
              JOptionPane.PLAIN_MESSAGE);
        }
      }
    }
  }
}
