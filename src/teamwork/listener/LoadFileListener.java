package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import teamwork.controler.NewsLoadingControler;
import teamwork.loader.NewsLoader;
import teamwork.model.NewsCatalog;
import teamwork.model.NewsListModel;
import teamwork.r.R;

@SuppressWarnings("rawtypes")
public class LoadFileListener implements ActionListener {

  private JList newsList;
  private JLabel numLabel;

  public LoadFileListener() {
    R r = R.getInstance();
    newsList = (JList) r.getObject("newsList");
    numLabel = (JLabel) r.getObject("numLabel");
  }

  public void actionPerformed(ActionEvent e) {
    JFileChooser jfc = new JFileChooser();
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
    jfc.addChoosableFileFilter(new XmlFileFilter("xml"));

    int state = jfc.showOpenDialog(null);
    if (state == JFileChooser.APPROVE_OPTION) {
      NewsLoadingControler dataLoadingControler = new NewsLoadingControler(new NewsLoader());

      File file = jfc.getSelectedFile();
      if (!dataLoadingControler.loadData(file.getAbsolutePath())) {
        JOptionPane.showMessageDialog(null, "文件格式错误！", "错误", JOptionPane.ERROR_MESSAGE);
      } else {
        ((NewsListModel) newsList.getModel()).notifyDataChanged();
        numLabel.setText("一共有 " + NewsCatalog.getInstance().size() + " 条新闻");
      }
    }

  }

  // JFileChoose的文件过滤器，筛选XML文件
  private class XmlFileFilter extends FileFilter {
    String xml;

    public XmlFileFilter(String xml) {
      this.xml = xml;
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
        if (extension.equals(xml)) {
          return true;
        }
      }
      return false;
    }

    @Override
    public String getDescription() {
      if (xml.equals("xml")) {
        return "*.xml";
      }
      return "";
    }
  }
}
