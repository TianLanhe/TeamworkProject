package teamwork.listener;

import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import teamwork.model.controler.ImportControler;
import teamwork.model.viewmodel.NewsTreeModel;
import teamwork.r.R;

public class ImportListener extends IOListener {

  private JTree tagsTree;

  public ImportListener() {
    tagsTree = (JTree) R.getInstance().getObject("statisticsTagsTree");
  }

  @Override
  protected int showDialog() {
    return jfc.showDialog(null, "导入");
  }

  @Override
  protected void saveOrLoad() {
    File file = jfc.getSelectedFile();
    if (!file.exists()) {
      JOptionPane.showMessageDialog(null, "文件不存在，读取失败！", "错误", JOptionPane.ERROR_MESSAGE);
    } else {
      ImportControler importControler = new ImportControler();

      String pwdCheck = new String(pw.getPassword());
      String pwd = importControler.loadPassword(file);

      // 密码不符
      if (!pwd.equals(pwdCheck)) {
        JOptionPane.showMessageDialog(null, "您输入的密码与文件密码不符,读取失败！", "错误", JOptionPane.ERROR_MESSAGE);
        return;
      }

      if (!importControler.load(file)) {
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
