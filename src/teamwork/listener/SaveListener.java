package teamwork.listener;

import java.io.File;

import javax.swing.JOptionPane;

import teamwork.model.controler.SaveControler;

public class SaveListener extends IOListener {

  @Override
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
      }
    }
    // �����ļ�
    if (!saveControler.save(file)) {
      JOptionPane.showMessageDialog(null, "����ʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
    }
  }
}
