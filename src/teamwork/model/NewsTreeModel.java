package teamwork.model;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class NewsTreeModel extends DefaultTreeModel {

  private static final long serialVersionUID = -5684540436214446650L;

  private ClassCatalog classCatalog;

  public NewsTreeModel(ClassCatalog catalog) {
    super(null);

    this.classCatalog = catalog;
    updateTree();
  }

  // ��������Ѱ�ҽڵ㣬�����ظ��ڵ㵽�ýڵ�Ľڵ�����
  public TreeNode[] getPathToRoot(String name) {
    DefaultMutableTreeNode node = getPathToRoot((DefaultMutableTreeNode) getRoot(), name);
    return node == null ? null : node.getPath();
  }

  // �ݹ�Ѱ��ָ�����ֵĽڵ㣬�ҵ��ⷵ�ؽڵ����ã����򷵻�null
  private DefaultMutableTreeNode getPathToRoot(DefaultMutableTreeNode node, String name) {
    if (node.getUserObject().toString().equals(name)) {
      return node;
    } else {
      for (int i = 0; i < node.getChildCount(); ++i) {
        DefaultMutableTreeNode n = getPathToRoot((DefaultMutableTreeNode) node.getChildAt(i), name);
        if (n != null) {
          return n;
        }
      }
    }
    return null;
  }

  // ����ģ�ͣ���֪ͨ��ͼ���и���
  public void updateTree() {
    TreeNode root = createTreeRoot();
    setRoot(root);
  }

  // ����ClassCatalog�е��������ģ��
  private TreeNode createTreeRoot() {
    DefaultMutableTreeNode root = (DefaultMutableTreeNode) createTreeNode(classCatalog);
    return root;
  }

  // �ݹ鴴��ģ��
  private TreeNode createTreeNode(Object obj) {
    DefaultMutableTreeNode node = new DefaultMutableTreeNode(obj);

    if (obj instanceof Tag) {
      Tag tag = (Tag) obj;
      if (tag.hasNextClass()) {
        NewsClass c = tag.getNextClass();
        for (Tag t : c.getTagsList()) {
          node.add((MutableTreeNode) createTreeNode(t)); // �����������ֱ����ʾ��ǩ
        }
      }
    } else if (obj instanceof NewsClass) {
      NewsClass c = (NewsClass) obj;
      for (Tag t : c.getTagsList()) {
        node.add((MutableTreeNode) createTreeNode(t));
      }
    } else if (obj instanceof ClassCatalog) {
      ClassCatalog classCatalog = (ClassCatalog) obj;
      for (NewsClass c : classCatalog.getClassNotRelatedToTag()) { // ֻ��û�й�����Tag����������ʾ
        node.add((MutableTreeNode) createTreeNode(c));
      }
    }
    return node;
  }
}
