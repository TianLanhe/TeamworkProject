package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import teamwork.controler.NewsLoadingControler;
import teamwork.loader.NewsLoader;

public class LoadFileListener implements ActionListener {
  public void actionPerformed(ActionEvent e) {
    JFileChooser jfc = new JFileChooser();
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
    jfc.addChoosableFileFilter(new XmlFileFilter("xml"));

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
class XmlFileFilter extends FileFilter{
  
  String xml;
  
  public XmlFileFilter(String xml) {
    // TODO Auto-generated constructor stub
    this.xml = xml;
  }

  @Override
  public boolean accept(File f) {
    // TODO Auto-generated method stub
    if (f.isDirectory()) {
      return true;
    }
    String fileName = f.getName();
    int index = fileName.lastIndexOf('.');
    if (index > 0 &&  index < fileName.length() - 1) {
      String extension = fileName.substring(index + 1).toLowerCase();
      if (extension.equals(xml)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String getDescription() {
    // TODO Auto-generated method stub
    if (xml.equals("xml")) {
      return "*.xml";
    }
    return "";
  }
  
}