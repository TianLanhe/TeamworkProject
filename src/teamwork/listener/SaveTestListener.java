package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.prefs.Preferences;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.filechooser.FileNameExtensionFilter;

import teamwork.model.controler.SaveControler;

public class SaveTestListener implements ActionListener {

  private JFileChooser jfc = new JFileChooser();
  
  @Override
  public void actionPerformed(ActionEvent e) {
  
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
    jfc.setSelectedFile(new File(".test"));
    jfc.setCurrentDirectory(jfc.getSelectedFile());
    FileNameExtensionFilter filter = new FileNameExtensionFilter(  
      "�����ļ�(*.test)", "test");  
    jfc.setFileFilter(filter); 
    jfc.setCurrentDirectory(jfc.getSelectedFile());
   
    int state = jfc.showSaveDialog(null);
    if (state == JFileChooser.APPROVE_OPTION) {
      String name = jfc.getSelectedFile().getName();
      if (name.length() < 5 || name.indexOf(".test") != name.length() - 5) {
        name += ".test";
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
      }
    }
  }
}