package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.filechooser.FileNameExtensionFilter;

public abstract class IOListener implements ActionListener {

  protected JFileChooser jfc;// �ļ�ѡ���
  protected JPasswordField pw;// ���������

  // ��ʾѡ��򲢷���ѡ��
  protected abstract int showDialog();

  // ������ȡ�ľ����߼�
  protected abstract void saveOrLoad();

  @Override
  public void actionPerformed(ActionEvent arg0) {
    // �����ļ�ѡ����
    jfc = new JFileChooser();
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
    FileNameExtensionFilter filter = new FileNameExtensionFilter("ͳ���ļ�(*.sfs)", "sfs");
    jfc.setFileFilter(filter);

    // ���ѡ����"ȷ��"
    int state = showDialog();
    if (state == JFileChooser.APPROVE_OPTION) {
      int ret;
      do {
        // �������������
        ret = JOptionPane.showConfirmDialog(null, createPanel(), "��ʾ", JOptionPane.YES_NO_OPTION);
        if (ret == JOptionPane.YES_OPTION && pw.getPassword().length == 0) {
          JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
        }
      } while (ret == JOptionPane.YES_OPTION && pw.getPassword().length == 0);

      if (ret == JOptionPane.YES_OPTION) {
        saveOrLoad();
      }
    }
  }

  // ����һ��������ʾ����������ݿ����岢����
  private JPanel createPanel() {
    JPanel panel = new JPanel();
    panel.add(new JLabel("���������룺"));

    pw = new JPasswordField(15);
    pw.setSize(50, 100);
    panel.add(pw);

    return panel;
  }
}
