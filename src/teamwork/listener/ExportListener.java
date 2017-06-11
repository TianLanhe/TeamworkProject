package teamwork.listener;

import java.io.File;

import javax.swing.JOptionPane;

import teamwork.model.controler.ExportControler;

public class ExportListener extends IOListener {

  @Override
  protected int showDialog() {
    return jfc.showDialog(null, "导出");
  }

  @Override
  protected void saveOrLoad() {
    ExportControler exportControler = new ExportControler();

    // 保存密码
    exportControler.addPassword(pw.getPassword());

    String name = jfc.getSelectedFile().getName();
    if (!name.endsWith(".sfs")) {
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
    // 保存文件
    if (!exportControler.save(file)) {
      JOptionPane.showMessageDialog(null, "保存失败！", "错误", JOptionPane.ERROR_MESSAGE);
    }
  }

}
