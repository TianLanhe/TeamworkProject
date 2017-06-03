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

      }
      SaveControler saveControler = new SaveControler();
      if (!saveControler.save(file)) {
        JOptionPane.showMessageDialog(null, "����ʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
=======
  protected int showDialog() {
    return jfc.showSaveDialog(null);
  }

  @Override
  protected void saveOrLoad() {
    SaveControler saveControler = new SaveControler();

    // ��������
    saveControler.addPassword(pw.getPassword());

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
>>>>>>> 893a680e9d0e9e6f1755f65c6d35201d68af7d5e
      }
    }
    // �����ļ�
    if (!saveControler.save(file)) {
      JOptionPane.showMessageDialog(null, "����ʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
    }
  }
}
