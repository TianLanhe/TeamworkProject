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

  // �������Ʒ��ظ��ڵ㵽���ڵ����б�ǩ������ FIXME
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

  // ����TreeNode���ظ��ڵ㵽���ڵ����б�ǩ������
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

  // �������Ʒ��ظ��ڵ㵽���ڵ��TreeNode���� FIXME
  public TreeNode[] getPathToRoot(String name) {
    DefaultMutableTreeNode node = getPathToRoot((DefaultMutableTreeNode) getRoot(), name);
    return node == null ? null : node.getPath();
  }

  // �ݹ�Ѱ��ָ�����ֵĽڵ㣬�ҵ��򷵻ؽڵ����ã����򷵻�null
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

  // ����ClassCatalog�е����ͱ�ǩ����ģ��
  private TreeNode createTreeRoot() {
    return createTreeNode(classCatalog);
  }

  // �ݹ鴴��ģ��
  private DefaultMutableTreeNode createTreeNode(Object obj) {
    DefaultMutableTreeNode node = new DefaultMutableTreeNode(obj);

    if (obj instanceof Tag) {
      Tag tag = (Tag) obj;
      if (tag.hasNextClass()) {
        NewsClass c = tag.getNextClass();
        for (Tag t : c.getTagsList()) {
          node.add(createTreeNode(t)); // �����������ֱ����ʾ��ǩ
        }
      }
    } else if (obj instanceof NewsClass) {
      NewsClass c = (NewsClass) obj;
      for (Tag t : c.getTagsList()) {
        node.add(createTreeNode(t));
      }
    } else if (obj instanceof ClassCatalog) {
      ClassCatalog classCatalog = (ClassCatalog) obj;
      for (NewsClass c : classCatalog.getClassNotRelatedToTag()) { // ֻ��û�й�����Tag����������ʾ
        node.add(createTreeNode(c));
      }
    }
    return node;
  }
}
