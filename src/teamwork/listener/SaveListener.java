package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
<<<<<<< HEAD
import javax.swing.JPasswordField;
=======
>>>>>>> 59a0402e094247825f0de1aac79410f6a9dd6c9e
import javax.swing.filechooser.FileNameExtensionFilter;

import teamwork.model.controler.SaveControler;

public class SaveListener implements ActionListener {

<<<<<<< HEAD
  private BufferedWriter out;
  private FileWriter fileWriter;
  private int returnVal;
  private char[] pwd;

=======
>>>>>>> 59a0402e094247825f0de1aac79410f6a9dd6c9e
  @Override
  public void actionPerformed(ActionEvent e) {
    JFileChooser jfc = new JFileChooser();
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
<<<<<<< HEAD
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
      try {
        File dir = jfc.getCurrentDirectory();
        String name = jfc.getSelectedFile().getName();
        File file = new File(dir, name);
        Object[] options = {"是的","取消"};
       
        if(file.exists()){//已存在文件
          JOptionPane.showOptionDialog(null, file + "文件已经存在,是否覆盖?", "文件存在", JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE,null,options,options[0]);//显示一个对话框来实现是否覆盖源文件
        }
        
        if(name.indexOf(".mmp")==-1){
          file = new File(jfc.getCurrentDirectory(),name+".mmp");
        }
       
        
        fileWriter = new FileWriter(file);
        out = new BufferedWriter(fileWriter);
        out.close();
        fileWriter.close();
      } catch (IOException exp) {
        System.err.println("输入异常");  
        exp.printStackTrace();
=======
    FileNameExtensionFilter filter = new FileNameExtensionFilter("进度文件(*.mmp)", "mmp");
    jfc.setFileFilter(filter);

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
>>>>>>> 59a0402e094247825f0de1aac79410f6a9dd6c9e
      }
      SaveControler saveControler = new SaveControler();
      if (!saveControler.save(file)) {
        JOptionPane.showMessageDialog(null, "保存失败！", "错误", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
}
