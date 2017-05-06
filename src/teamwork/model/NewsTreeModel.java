package teamwork.model;


import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

public class NewsTreeModel extends DefaultTreeModel {

  private static final long serialVersionUID = -5684540436214446650L;

  private ClassCatalog classCatalog;

  public NewsTreeModel(ClassCatalog catalog) {
    super(null);

    this.classCatalog = catalog;
    updateTree();
  }

  // 根据名称返回根节点到最后节点所有标签的数组 FIXME
  public Tag[] getTagsFromRoot(String lastName) {
    TreeNode[] path = getPathToRoot(lastName);
    if (path == null) {
      return null;
    }

    Tag[] tags = new Tag[path.length];
    int count = 0;
    for (TreeNode n : path) {
      DefaultMutableTreeNode dn = (DefaultMutableTreeNode) n;
      if (dn.getUserObject() instanceof Tag) {
        tags[count++] = (Tag) dn.getUserObject();
      }
    }

    Tag[] ret = new Tag[count];
    System.arraycopy(tags, 0, ret, 0, count);
    return ret;
  }

  // 根据TreeNode返回根节点到最后节点所有标签的数组
  public Tag[] getTagsFromRoot(TreeNode node) {
    TreeNode[] path = getPathToRoot(node);
    if (path == null || path.length == 0) {
      return null;
    }

    Tag[] tags = new Tag[path.length];
    int count = 0;
    for (TreeNode n : path) {
      DefaultMutableTreeNode dn = (DefaultMutableTreeNode) n;
      if (dn.getUserObject() instanceof Tag) {
        tags[count++] = (Tag) dn.getUserObject();
      }
    }

    Tag[] ret = new Tag[count];
    System.arraycopy(tags, 0, ret, 0, count);
    return ret;
  }

  // 根据名称返回根节点到最后节点的TreeNode数组 FIXME
  public TreeNode[] getPathToRoot(String name) {
    DefaultMutableTreeNode node = getPathToRoot((DefaultMutableTreeNode) getRoot(), name);
    return node == null ? null : node.getPath();
  }

  // 递归寻找指定名字的节点，找到则返回节点引用，否则返回null
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

  // 更新模型，并通知视图进行更新
  public void updateTree() {
    TreeNode root = createTreeRoot();
    setRoot(root);
  }

  // 根据ClassCatalog中的类别和标签创建模型
  private TreeNode createTreeRoot() {
    return createTreeNode(classCatalog);
  }

  // 递归创建模型
  private DefaultMutableTreeNode createTreeNode(Object obj) {
    DefaultMutableTreeNode node = new DefaultMutableTreeNode(obj);

    if (obj instanceof Tag) {
      Tag tag = (Tag) obj;
      if (tag.hasNextClass()) {
        NewsClass c = tag.getNextClass();
        for (Tag t : c.getTagsList()) {
          node.add(createTreeNode(t)); // 跳过关联类别，直接显示标签
        }
      }
    } else if (obj instanceof NewsClass) {
      NewsClass c = (NewsClass) obj;
      for (Tag t : c.getTagsList()) {
        node.add(createTreeNode(t));
      }
    } else if (obj instanceof ClassCatalog) {
      ClassCatalog classCatalog = (ClassCatalog) obj;
      for (NewsClass c : classCatalog.getClassNotRelatedToTag()) { // 只有没有关联到Tag的类别才能显示
        node.add(createTreeNode(c));
      }
    }
    return node;
  }
}
