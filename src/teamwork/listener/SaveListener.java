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
      "�����ļ�(*.mmp)", "mmp");  
    jfc.setFileFilter(filter); 
    jfc.setCurrentDirectory(jfc.getSelectedFile());
    
    returnVal = jfc.showOpenDialog(null);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      JPasswordField pw = new JPasswordField();
      JOptionPane.showMessageDialog(null, pw, "���趨6λ����", JOptionPane.PLAIN_MESSAGE);
      
      pwd = pw.getPassword();
      while(pwd.length != 6){
        JOptionPane.showMessageDialog(null,pw,"�������Ϊ6λ", JOptionPane.PLAIN_MESSAGE);
        pwd = pw.getPassword();
        }
    }
    
    int state = jfc.showSaveDialog(null);
    if (state == JFileChooser.APPROVE_OPTION) {
      try {
        File dir = jfc.getCurrentDirectory();
        String name = jfc.getSelectedFile().getName();
        File file = new File(dir, name);
        Object[] options = {"�ǵ�","ȡ��"};
       
        if(file.exists()){//�Ѵ����ļ�
          JOptionPane.showOptionDialog(null, file + "�ļ��Ѿ�����,�Ƿ񸲸�?", "�ļ�����", JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE,null,options,options[0]);//��ʾһ���Ի�����ʵ���Ƿ񸲸�Դ�ļ�
        }
        
        if(name.indexOf(".mmp")==-1){
          file = new File(jfc.getCurrentDirectory(),name+".mmp");
        }
       
        
        fileWriter = new FileWriter(file);
        out = new BufferedWriter(fileWriter);
        out.close();
        fileWriter.close();
      } catch (IOException exp) {
        System.err.println("�����쳣");  
        exp.printStackTrace();
=======
    FileNameExtensionFilter filter = new FileNameExtensionFilter("�����ļ�(*.mmp)", "mmp");
    jfc.setFileFilter(filter);

    int state = jfc.showSaveDialog(null);
    if (state == JFileChooser.APPROVE_OPTION) {
      String name = jfc.getSelectedFile().getName();
      if (name.length() < 4 || name.indexOf(".mmp") != name.length() - 4) {
        name += ".mmp";
      }

      File file = new File(jfc.getCurrentDirectory(), name);
      if (file.exists()) {// �Ѵ����ļ�
        int flag =
            JOptionPane.showConfirmDialog(null, file + "�ļ��Ѿ�����,�Ƿ񸲸�?", "�ļ�����",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);// ��ʾһ���Ի�����ʵ���Ƿ񸲸�Դ�ļ�
        if (flag == JOptionPane.NO_OPTION) {
          return;
        }
>>>>>>> 59a0402e094247825f0de1aac79410f6a9dd6c9e
      }
      SaveControler saveControler = new SaveControler();
      if (!saveControler.save(file)) {
        JOptionPane.showMessageDialog(null, "����ʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
}
