package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.filechooser.FileNameExtensionFilter;


public class SaveListener implements ActionListener {

  private BufferedWriter out;
  private FileWriter fileWriter;
  private int returnVal;
  private char[] pwd;

  @Override
  public void actionPerformed(ActionEvent e) {
    JFileChooser jfc = new JFileChooser();
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
      }
    
    }
  }

}
