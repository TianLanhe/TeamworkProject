package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DoTestListener implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
    JFileChooser jfc = new JFileChooser();
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);// ֻ��ѡ���ļ�
    jfc.setMultiSelectionEnabled(true);// �����ѡ
    FileNameExtensionFilter filter = new FileNameExtensionFilter("�����ļ�(*.test)", "test");
    jfc.setFileFilter(filter);

    int state = jfc.showDialog(null, "��ȡ");
    if (state == JFileChooser.APPROVE_OPTION) {
      File[] files = jfc.getSelectedFiles();
      if (files.length != 2) {
        JOptionPane
            .showMessageDialog(null, "��ѡ�����������ļ����в��ԣ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
      } else {
        // �������ѡ���ļ��Ƿ����
        for (File file : files) {
          if (!file.exists()) {
            JOptionPane.showMessageDialog(null, "�ļ������ڣ���ȡʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
            return;
          }
        }
        // TODO
      }
    }
  }
}
