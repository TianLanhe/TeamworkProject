package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< HEAD
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
=======
import java.io.File;
>>>>>>> 4a2f2d51d824c6c09fe6cbd99ed9087fba35c98b

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class LoadListener implements ActionListener {

<<<<<<< HEAD
  private FileWriter fileWriter;
  private BufferedWriter out;
  
  @Override
  public void actionPerformed(ActionEvent e) {
=======

  public void actionPerformed(ActionEvent e) {
    // 创建文件选择器
>>>>>>> 4a2f2d51d824c6c09fe6cbd99ed9087fba35c98b
    JFileChooser jfc = new JFileChooser();
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
    jfc.addChoosableFileFilter(new MmpFileFilter("mmp"));

<<<<<<< HEAD
    if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

    }
    int state = jfc.showSaveDialog(null);
    if (state == JFileChooser.APPROVE_OPTION) {
      try {
        File dir = jfc.getCurrentDirectory();
        String name = jfc.getSelectedFile().getName();
        File file = new File(dir, name);
        fileWriter = new FileWriter(file);
        out = new BufferedWriter(fileWriter);
        out.close();
        fileWriter.close();
      } catch (IOException exp) {}
    }
  }

=======
    int state = jfc.showOpenDialog(null);
    if (state == JFileChooser.APPROVE_OPTION) {
      
     
      
    }
  }

  // JFileChoose的文件过滤器，筛选XML文件
>>>>>>> 4a2f2d51d824c6c09fe6cbd99ed9087fba35c98b
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
<<<<<<< HEAD


=======
>>>>>>> 4a2f2d51d824c6c09fe6cbd99ed9087fba35c98b
}
