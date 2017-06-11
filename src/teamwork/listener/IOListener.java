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

  protected JFileChooser jfc;// 文件选择框
  protected JPasswordField pw;// 密码输入框

  // 显示选择框并返回选择
  protected abstract int showDialog();

  // 保存或读取的具体逻辑
  protected abstract void saveOrLoad();

  @Override
  public void actionPerformed(ActionEvent arg0) {
    // 创建文件选择器
    jfc = new JFileChooser();
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
    FileNameExtensionFilter filter = new FileNameExtensionFilter("统计文件(*.sfs)", "sfs");
    jfc.setFileFilter(filter);

    // 如果选择了"确定"
    int state = showDialog();
    if (state == JFileChooser.APPROVE_OPTION) {
      int ret;
      do {
        // 弹出密码输入框
        ret = JOptionPane.showConfirmDialog(null, createPanel(), "提示", JOptionPane.YES_NO_OPTION);
        if (ret == JOptionPane.YES_OPTION && pw.getPassword().length == 0) {
          JOptionPane.showMessageDialog(null, "密码不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
      } while (ret == JOptionPane.YES_OPTION && pw.getPassword().length == 0);

      if (ret == JOptionPane.YES_OPTION) {
        saveOrLoad();
      }
    }
  }

  // 创建一个包含提示语和密码数据框的面板并返回
  private JPanel createPanel() {
    JPanel panel = new JPanel();
    panel.add(new JLabel("请输入密码："));

    pw = new JPasswordField(15);
    pw.setSize(50, 100);
    panel.add(pw);

    return panel;
  }
}
