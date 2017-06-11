package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import teamwork.model.controler.SaveControler;

public class SaveListener implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent arg0) {
    // �����ļ�ѡ����
    JFileChooser jfc = new JFileChooser();
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);// ֻ��ѡ���ļ�
    FileNameExtensionFilter filter = new FileNameExtensionFilter("�����ļ�(*.mmp)", "mmp");
    jfc.setFileFilter(filter);

    // ���ѡ����"ȷ��"
    int state = jfc.showSaveDialog(null);
    if (state == JFileChooser.APPROVE_OPTION) {
      String name = jfc.getSelectedFile().getName();
      if (!name.endsWith(".mmp")) {
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
      // �����ļ�
      if (!new SaveControler().save(file)) {
        JOptionPane.showMessageDialog(null, "����ʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
}
