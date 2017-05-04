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

  public void updateTree() {
    TreeNode root = createTreeRoot();
    setRoot(root);
  }

  private TreeNode createTreeRoot() {
    DefaultMutableTreeNode root = (DefaultMutableTreeNode) createTreeNode(classCatalog);
    return root;
  }

  private TreeNode createTreeNode(Object obj) {
    DefaultMutableTreeNode node = new DefaultMutableTreeNode(obj);

    if (obj instanceof Tag) {
      Tag tag = (Tag) obj;
      if (tag.hasNextClass()) {
        NewsClass c = tag.getNextClass();
        for (Tag t : c.getTagsList()) {
          node.add((MutableTreeNode) createTreeNode(t));
        }
      }
    } else if (obj instanceof NewsClass) {
      NewsClass c = (NewsClass) obj;
      for (Tag t : c.getTagsList()) {
        node.add((MutableTreeNode) createTreeNode(t));
      }
    } else if (obj instanceof ClassCatalog) {
      ClassCatalog classCatalog = (ClassCatalog) obj;
      for (NewsClass c : classCatalog.getClassList()) {
        node.add((MutableTreeNode) createTreeNode(c));
      }
    }
    return node;
  }
}
