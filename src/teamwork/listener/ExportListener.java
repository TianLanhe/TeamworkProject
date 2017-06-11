package teamwork.listener;

import java.io.File;

import javax.swing.JOptionPane;

import teamwork.model.controler.ExportControler;

public class ExportListener extends IOListener {

  @Override
  protected int showDialog() {
    return jfc.showDialog(null, "����");
  }

  @Override
  protected void saveOrLoad() {
    ExportControler exportControler = new ExportControler();

    // ��������
    exportControler.addPassword(pw.getPassword());

    String name = jfc.getSelectedFile().getName();
    if (!name.endsWith(".sfs")) {
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
    // �����ļ�
    if (!exportControler.save(file)) {
      JOptionPane.showMessageDialog(null, "����ʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
    }
  }

}
