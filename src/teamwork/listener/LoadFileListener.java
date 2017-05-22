package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.filechooser.FileFilter;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import teamwork.controler.NewsLoadingControler;
import teamwork.loader.NewsLoader;
import teamwork.model.NewsTreeModel;
import teamwork.r.R;

public class LoadFileListener implements ActionListener {

  private JTree tagsTree;

  public LoadFileListener() {
    R r = R.getInstance();
    tagsTree = (JTree) r.getObject("tagsTree");
  }

  public void actionPerformed(ActionEvent e) {
    // �����ļ�ѡ����
    JFileChooser jfc = new JFileChooser();
    jfc.setCurrentDirectory(jfc.getSelectedFile()); 
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
    jfc.addChoosableFileFilter(new XmlFileFilter("xml"));
    
    int state = jfc.showOpenDialog(null);
    if (state == JFileChooser.APPROVE_OPTION) {
      NewsLoadingControler dataLoadingControler = new NewsLoadingControler(new NewsLoader());

      File file = jfc.getSelectedFile();
      // ��ȡ���ļ�������
      int count = dataLoadingControler.loadData(file.getAbsolutePath());
      if (count == -1) {
        JOptionPane.showMessageDialog(null, "�ļ���ʽ����", "����", JOptionPane.ERROR_MESSAGE);
      } else {
        // �������ṹ
        NewsTreeModel model = (NewsTreeModel) tagsTree.getModel();
        model.updateTree();

        TreeNode[] nodes = model.getPathToRoot("δ����");
        TreePath path = new TreePath(nodes);
        // ��ʾ"δ����"��ǩ�µ�����
        tagsTree.setSelectionPath(path);

        JOptionPane.showMessageDialog(null, "�ɹ���ȡ " + count + " ������", "���سɹ�",
            JOptionPane.INFORMATION_MESSAGE);
      }
    }
  }

  // JFileChoose���ļ���������ɸѡXML�ļ�
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
      return "*." + xml;
    }
  }
}
