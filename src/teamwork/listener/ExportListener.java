package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ExportListener implements ActionListener{

  @Override
  public void actionPerformed(ActionEvent e) {
    JFileChooser jfc = new JFileChooser();
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);//ֻ��ѡ���ļ�
    FileNameExtensionFilter filter = new FileNameExtensionFilter("ͳ���ļ�(*.sfs)", "sfs");
    jfc.setDialogTitle("����");
    jfc.setFileFilter(filter);

    int state = jfc.showSaveDialog(null);
    if (state == JFileChooser.APPROVE_OPTION) {
      String name = jfc.getSelectedFile().getName();
      
      if (name.length() < 4 || name.indexOf(".sfs") != name.length() - 4) {
        name += ".sfs";
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
     
    }
    
  }

}
