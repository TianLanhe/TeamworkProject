package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class LoadListener implements ActionListener {


  public void actionPerformed(ActionEvent e) {
    // 创建文件选择器
    JFileChooser jfc = new JFileChooser();
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
    jfc.addChoosableFileFilter(new MmpFileFilter("mmp"));

    int state = jfc.showOpenDialog(null);
    if (state == JFileChooser.APPROVE_OPTION) {
      
     
      
    }
  }

  // JFileChoose的文件过滤器，筛选XML文件
  private class MmpFileFilter extends FileFilter {
    String mmp;

    public MmpFileFilter(String mmp) {
      this.mmp = mmp;
    }

    @Override
    public boolean accept(File f) {
      if (f.isDirectory()) {
        return true;
      }
      String fileName = f.getName();
      int index = fileName.lastIndexOf('.');
      if (index > 0 && index < fileName.length() - 1) {
        String extension = fileName.substring(index + 1).toLowerCase();
        if (extension.equals(mmp)) {
          return true;
        }
      }
      return false;
    }

    @Override
    public String getDescription() {
      return "*." + mmp;
    }
  }
}
