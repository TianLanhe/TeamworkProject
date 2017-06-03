package teamwork.listener;

<<<<<<< HEAD
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
=======
>>>>>>> 893a680e9d0e9e6f1755f65c6d35201d68af7d5e
import java.io.File;
import java.io.FileWriter;

import javax.swing.JOptionPane;
<<<<<<< HEAD

import javax.swing.JPasswordField;
import javax.swing.filechooser.FileNameExtensionFilter;
=======
>>>>>>> 893a680e9d0e9e6f1755f65c6d35201d68af7d5e

import teamwork.model.controler.SaveControler;

public class SaveListener extends IOListener {

  private int returnVal;
  private char[] pwd;
  private JFileChooser jfc = new JFileChooser();

  @Override
<<<<<<< HEAD
  public void actionPerformed(ActionEvent e) {
    
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);

    jfc.setSelectedFile(new File(".mmp"));
    FileNameExtensionFilter filter = new FileNameExtensionFilter(  
      "进度文件(*.mmp)", "mmp");  
    jfc.setFileFilter(filter); 
    jfc.setCurrentDirectory(jfc.getSelectedFile());
    
    returnVal = jfc.showOpenDialog(null);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      JPasswordField pw = new JPasswordField();
      JOptionPane.showMessageDialog(null, pw, "请设定6位密码", JOptionPane.PLAIN_MESSAGE);
      
      pwd = pw.getPassword();
      while(pwd.length != 6){
        JOptionPane.showMessageDialog(null,pw,"密码必须为6位", JOptionPane.PLAIN_MESSAGE);
        pwd = pw.getPassword();
        }
    }
    
    int state = jfc.showSaveDialog(null);
    if (state == JFileChooser.APPROVE_OPTION) {
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
      SaveControler saveControler = new SaveControler();
      if (!saveControler.save(file)) {
        JOptionPane.showMessageDialog(null, "保存失败！", "错误", JOptionPane.ERROR_MESSAGE);
=======
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
>>>>>>> 893a680e9d0e9e6f1755f65c6d35201d68af7d5e
      }
    }
    // 保存文件
    if (!saveControler.save(file)) {
      JOptionPane.showMessageDialog(null, "保存失败！", "错误", JOptionPane.ERROR_MESSAGE);
    }
  }
}
