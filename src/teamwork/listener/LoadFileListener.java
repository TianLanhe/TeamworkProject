package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import teamwork.controler.NewsLoadingControler;
import teamwork.loader.NewsLoader;

public class LoadFileListener implements ActionListener {
  public void actionPerformed(ActionEvent e) {
    JFileChooser jfc = new JFileChooser();
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);

    int state = jfc.showOpenDialog(null);
    if (state == JFileChooser.APPROVE_OPTION) {
      NewsLoadingControler dataLoadingControler = new NewsLoadingControler(new NewsLoader());

      File file = jfc.getSelectedFile();
      if(!dataLoadingControler.loadData(file.getAbsolutePath())){
        JOptionPane.showMessageDialog(null, "文件格式错误！", "错误", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
}
