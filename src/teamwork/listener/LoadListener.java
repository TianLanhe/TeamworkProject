package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import teamwork.model.controler.LoadControler;
import teamwork.model.viewmodel.NewsTreeModel;
import teamwork.r.R;

public class LoadListener implements ActionListener {

  private JTree tagsTree;

  public LoadListener() {
    tagsTree = (JTree) R.getInstance().getObject("tagsTree");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // 创建文件选择器
    JFileChooser jfc = new JFileChooser();
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
    FileNameExtensionFilter filter = new FileNameExtensionFilter("进度文件(*.mmp)", "mmp");
    jfc.setFileFilter(filter);

    int state = jfc.showDialog(null, "读取");
    if (state == JFileChooser.APPROVE_OPTION) {
      File file = jfc.getSelectedFile();
      if (!file.exists()) {
        JOptionPane.showMessageDialog(null, "文件不存在，读取失败！", "错误", JOptionPane.ERROR_MESSAGE);
      } else {
        LoadControler loadControler = new LoadControler();
        if (!loadControler.load(file)) {
          JOptionPane.showMessageDialog(null, "读取失败！", "错误", JOptionPane.ERROR_MESSAGE);
        }

        // 更新树结构
        NewsTreeModel model = (NewsTreeModel) tagsTree.getModel();
        model.updateTree();

        TreeNode[] nodes = model.getPathToRoot("未分类");
        TreePath path = new TreePath(nodes);
        // 显示"未分类"标签下的新闻
        tagsTree.setSelectionPath(path);
      }
    }
  }
}
