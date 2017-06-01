package teamwork.test;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import teamwork.model.ClassCatalog;
import teamwork.model.NewsClass;
import teamwork.model.Tag;
import teamwork.model.viewmodel.NewsTreeModel;

public class NewsTreeModelTest {

  private static ClassCatalog catalog;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    catalog = ClassCatalog.getInstance();
/*
 * 构建如下结构的树：
 * --┐
 *   |- 类别1 -┐
 *   |         |- 标签1-1
 *   |      
 *   |- 类别2 -┐
 *   |         |- 标签2-1 -┐
 *   |                     |- 类别5 -- 标签5-1
 *   |      
 *   |- 类别3 —┐
 *   |         |- 标签3-1-┐
 *   |         |          |- 类别6 -- 标签6-1
 *   |         |
 *   |         |- 标签3-2-┐
 *   |                    |- 类别6 -- 标签6-1
 *   |
 *   |- 类别4  
 */
    for (int i = 1; i <= 6; ++i) {
      NewsClass c = new NewsClass("类别" + i);
      catalog.add(c);
    }

    Tag t1_1 = new Tag("标签1-1", catalog.get("类别1"));
    Tag t2_1 = new Tag("标签2-1", catalog.get("类别2"), catalog.get("类别5"));
    Tag t3_1 = new Tag("标签3-1", catalog.get("类别3"), catalog.get("类别6"));
    Tag t3_2 = new Tag("标签3-2", catalog.get("类别3"), catalog.get("类别6"));
    Tag t5_1 = new Tag("标签5-1", catalog.get("类别5"));
    Tag t6_1 = new Tag("标签6-1", catalog.get("类别6"));

    catalog.get("类别1").addTag(t1_1);
    catalog.get("类别2").addTag(t2_1);
    catalog.get("类别3").addTag(t3_1);
    catalog.get("类别3").addTag(t3_2);
    catalog.get("类别5").addTag(t5_1);
    catalog.get("类别6").addTag(t6_1);
  }

  @AfterClass
  public static void afterClass() throws Exception {
    catalog.getClassList().clear();
  }

  @Test
  public void createTreeModelTest() {
    NewsTreeModel model = new NewsTreeModel(catalog);
    DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();

    Assert.assertEquals(4, root.getChildCount());
    Assert.assertEquals(3, root.getDepth());

    for (int i = 0; i < 4; ++i) {
      DefaultMutableTreeNode node = (DefaultMutableTreeNode) root.getChildAt(i);
      Assert.assertTrue(node.getUserObject() instanceof NewsClass);
      Assert.assertEquals(node.getUserObject().toString(), "类别" + (i + 1));

      if (i == 2) {
        Assert.assertEquals(2, node.getChildCount());
      } else if (i == 3) {
        Assert.assertEquals(0, node.getChildCount());
      } else {
        Assert.assertEquals(1, node.getChildCount());
      }

      for (int j = 0; j < node.getChildCount(); ++j) {
        DefaultMutableTreeNode tagNode = (DefaultMutableTreeNode) node.getChildAt(j);
        Assert.assertTrue(tagNode.getUserObject() instanceof Tag);
        Assert.assertEquals(tagNode.getUserObject().toString(), "标签" + (i + 1) + "-" + (j + 1));

        if (i == 1 || i == 2) {
          Assert.assertEquals(1, tagNode.getChildCount());
        } else {
          Assert.assertEquals(0, tagNode.getChildCount());
        }
      }
    }

    // 类别2 -> 标签2-1 -> 标签5-1
    DefaultMutableTreeNode node =
        (DefaultMutableTreeNode) root.getChildAt(1).getChildAt(0).getChildAt(0);
    Assert.assertTrue(node.getUserObject() instanceof Tag);
    Assert.assertEquals(node.getUserObject().toString(), "标签5-1");

    // 类别3 -> 标签3-1 -> 标签6-1
    node = (DefaultMutableTreeNode) root.getChildAt(2).getChildAt(0).getChildAt(0);
    Assert.assertTrue(node.getUserObject() instanceof Tag);
    Assert.assertEquals(node.getUserObject().toString(), "标签6-1");

    // 类别3 -> 标签3-2 -> 标签6-1
    node = (DefaultMutableTreeNode) root.getChildAt(2).getChildAt(1).getChildAt(0);
    Assert.assertTrue(node.getUserObject() instanceof Tag);
    Assert.assertEquals(node.getUserObject().toString(), "标签6-1");
  }

  @Test
  public void getPathFromRootByName() {
    NewsTreeModel model = new NewsTreeModel(catalog);

    TreeNode[] nodes = model.getPathToRoot("类别1");
    Assert.assertEquals(2, nodes.length);
    Assert.assertEquals(catalog, ((DefaultMutableTreeNode) nodes[0]).getUserObject());
    Assert.assertEquals("类别1", ((DefaultMutableTreeNode) nodes[1]).getUserObject().toString());

    nodes = model.getPathToRoot("标签2-1");
    Assert.assertEquals(3, nodes.length);
    Assert.assertEquals(catalog, ((DefaultMutableTreeNode) nodes[0]).getUserObject());
    Assert.assertEquals("类别2", ((DefaultMutableTreeNode) nodes[1]).getUserObject().toString());
    Assert.assertEquals("标签2-1", ((DefaultMutableTreeNode) nodes[2]).getUserObject().toString());

    nodes = model.getPathToRoot("标签3-2");
    Assert.assertEquals(3, nodes.length);
    Assert.assertEquals(catalog, ((DefaultMutableTreeNode) nodes[0]).getUserObject());
    Assert.assertEquals("类别3", ((DefaultMutableTreeNode) nodes[1]).getUserObject().toString());
    Assert.assertEquals("标签3-2", ((DefaultMutableTreeNode) nodes[2]).getUserObject().toString());

    // 注意：这里用标签名或类别名去寻找，但有可能名称出现在多处，可能无法找到想要的路径
    // FIXME
    nodes = model.getPathToRoot("标签6-1");
    Assert.assertEquals(4, nodes.length);
    Assert.assertEquals(catalog, ((DefaultMutableTreeNode) nodes[0]).getUserObject());
    Assert.assertEquals("类别3", ((DefaultMutableTreeNode) nodes[1]).getUserObject().toString());
    Assert.assertEquals("标签3-1", ((DefaultMutableTreeNode) nodes[2]).getUserObject().toString());
  }
}
