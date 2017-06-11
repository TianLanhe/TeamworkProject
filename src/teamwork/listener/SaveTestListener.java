package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import teamwork.model.News;
import teamwork.model.controler.SaveTestControler;
import teamwork.r.R;

public class SaveTestListener implements ActionListener {

  private JList<News> newsList;

  @SuppressWarnings("unchecked")
  public SaveTestListener() {
    R r = R.getInstance();
    newsList = (JList<News>) r.getObject("testNewsList");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (newsList.getModel().getSize() == 0) {
      JOptionPane.showMessageDialog(null, "���ȵ��\"�ҵĲ���\"��ȡ�����ļ���", "��ʾ",
          JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    JFileChooser jfc = new JFileChooser();
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);// ֻ��ѡ���ļ�
    FileNameExtensionFilter filter = new FileNameExtensionFilter("�����ļ�(*.test)", "test");
    jfc.setFileFilter(filter);

    int state = jfc.showSaveDialog(null);
    if (state == JFileChooser.APPROVE_OPTION) {
      String name = jfc.getSelectedFile().getName();
      if (!name.endsWith(".test")) {
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
      // �����ļ�
      if (!new SaveTestControler().save(file)) {
        JOptionPane.showMessageDialog(null, "����ʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
}
