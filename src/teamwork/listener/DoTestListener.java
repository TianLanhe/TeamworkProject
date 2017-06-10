package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import teamwork.model.controler.DoTest;

public class DoTestListener implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
    JFileChooser jfc = new JFileChooser();
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);// ֻ��ѡ���ļ�
    jfc.setMultiSelectionEnabled(true);// �����ѡ
    FileNameExtensionFilter filter = new FileNameExtensionFilter("�����ļ�(*.test)", "test");
    jfc.setFileFilter(filter);

    int state = jfc.showDialog(null, "����");
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

        double percent = new DoTest().test(files[0], files[1]);
        if (Math.abs(percent + 1) < 1e-5) {
          JOptionPane.showMessageDialog(null, "�ļ���ʽ������ļ����Բ�ͬ���Լ�������ʧ�ܣ�", "����",
              JOptionPane.ERROR_MESSAGE);
        } else {
          JOptionPane.showMessageDialog(null, "�����ļ�һ���Աȶԣ� " + (percent * 100) + "%", "���Խ��",
              JOptionPane.PLAIN_MESSAGE);
        }
      }
    }
  }
}
