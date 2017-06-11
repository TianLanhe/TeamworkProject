package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import teamwork.model.News;
import teamwork.model.controler.SaveTestControler;
import teamwork.r.R;

public class SaveTestListener implements ActionListener {

  private JList<News> newsList;

  @SuppressWarnings("unchecked")
  public SaveTestListener() {
    R r = R.getInstance();
    newsList = (JList<News>) r.getObject("testNewsList");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (newsList.getModel().getSize() == 0) {
      JOptionPane.showMessageDialog(null, "请先点击\"我的测试\"读取测试文件！", "提示",
          JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    JFileChooser jfc = new JFileChooser();
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);// 只能选择文件
    FileNameExtensionFilter filter = new FileNameExtensionFilter("测试文件(*.test)", "test");
    jfc.setFileFilter(filter);

    int state = jfc.showSaveDialog(null);
    if (state == JFileChooser.APPROVE_OPTION) {
      String name = jfc.getSelectedFile().getName();
      if (!name.endsWith(".test")) {
        name += ".test";
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
      // 保存文件
      if (!new SaveTestControler().save(file)) {
        JOptionPane.showMessageDialog(null, "保存失败！", "错误", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
}
