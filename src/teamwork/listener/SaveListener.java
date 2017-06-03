package teamwork.listener;

import java.io.File;

import javax.swing.JOptionPane;

import teamwork.model.controler.SaveControler;

public class SaveListener extends IOListener {

  @Override
  protected int showDialog() {
    return jfc.showSaveDialog(null);
  }

  @Override
  protected void saveOrLoad() {
    SaveControler saveControler = new SaveControler();

    // 保存密码
    saveControler.addPassword(pw.getPassword());

    String name = jfc.getSelectedFile().getName();
    if (name.length() < 4 || name.indexOf(".mmp") != name.length() - 4) {
      name += ".mmp";
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
    if (!saveControler.save(file)) {
      JOptionPane.showMessageDialog(null, "保存失败！", "错误", JOptionPane.ERROR_MESSAGE);
    }
  }
}
