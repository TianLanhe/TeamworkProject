package teamwork.listener;

import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import teamwork.model.controler.LoadControler;
import teamwork.model.viewmodel.NewsTreeModel;
import teamwork.r.R;

public class LoadListener extends IOListener {

  private JTree tagsTree;
  private JFileChooser jfc = new JFileChooser();
  
  public LoadListener() {
    tagsTree = (JTree) R.getInstance().getObject("tagsTree");
  }

  @Override
<<<<<<< HEAD
  public void actionPerformed(ActionEvent e) {
    // 创建文件选择器
    
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
=======
  protected int showDialog() {
    return jfc.showDialog(null, "读取");
  }

  @Override
  protected void saveOrLoad() {
    File file = jfc.getSelectedFile();
    if (!file.exists()) {
      JOptionPane.showMessageDialog(null, "文件不存在，读取失败！", "错误", JOptionPane.ERROR_MESSAGE);
    } else {
      LoadControler loadControler = new LoadControler();

      String pwdCheck = new String(pw.getPassword());
      String pwd = loadControler.loadPassword(file);

      // 密码不符
      if (!pwd.equals(pwdCheck)) {
        JOptionPane.showMessageDialog(null, "您输入的密码与文件密码不符,读取失败！", "错误", JOptionPane.ERROR_MESSAGE);
        return;
>>>>>>> 893a680e9d0e9e6f1755f65c6d35201d68af7d5e
      }

      if (!loadControler.load(file)) {
        JOptionPane.showMessageDialog(null, "读取失败！", "错误", JOptionPane.ERROR_MESSAGE);
        return;
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
