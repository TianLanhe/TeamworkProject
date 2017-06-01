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

import teamwork.controler.NewsLoadingControler;
import teamwork.loader.NewsLoader;
import teamwork.model.viewmodel.NewsTreeModel;
import teamwork.r.R;

public class LoadFileListener implements ActionListener {

  private JTree tagsTree;

  public LoadFileListener() {
    R r = R.getInstance();
    tagsTree = (JTree) r.getObject("tagsTree");
  }

  public void actionPerformed(ActionEvent e) {
    // 创建文件选择器
    JFileChooser jfc = new JFileChooser();
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
    FileNameExtensionFilter filter = new FileNameExtensionFilter(".xml", "xml");
    jfc.setFileFilter(filter);

    int state = jfc.showOpenDialog(null);
    if (state == JFileChooser.APPROVE_OPTION) {
      NewsLoadingControler dataLoadingControler = new NewsLoadingControler(new NewsLoader());

      File file = jfc.getSelectedFile();
      // 读取该文件的内容
      int count = dataLoadingControler.loadData(file.getAbsolutePath());
      if (count == -1) {
        JOptionPane.showMessageDialog(null, "文件格式错误！", "错误", JOptionPane.ERROR_MESSAGE);
      } else {
        // 更新树结构
        NewsTreeModel model = (NewsTreeModel) tagsTree.getModel();
        model.updateTree();

        TreeNode[] nodes = model.getPathToRoot("未分类");
        TreePath path = new TreePath(nodes);
        // 显示"未分类"标签下的新闻
        tagsTree.setSelectionPath(path);

        JOptionPane.showMessageDialog(null, "成功读取 " + count + " 条新闻", "加载成功",
            JOptionPane.INFORMATION_MESSAGE);
      }
    }
  }
}
