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
 * �������½ṹ������
 * --��
 *   |- ���1 -��
 *   |         |- ��ǩ1-1
 *   |      
 *   |- ���2 -��
 *   |         |- ��ǩ2-1 -��
 *   |                     |- ���5 -- ��ǩ5-1
 *   |      
 *   |- ���3 ����
 *   |         |- ��ǩ3-1-��
 *   |         |          |- ���6 -- ��ǩ6-1
 *   |         |
 *   |         |- ��ǩ3-2-��
 *   |                    |- ���6 -- ��ǩ6-1
 *   |
 *   |- ���4  
 */
    for (int i = 1; i <= 6; ++i) {
      NewsClass c = new NewsClass("���" + i);
      catalog.add(c);
    }

    Tag t1_1 = new Tag("��ǩ1-1", catalog.get("���1"));
    Tag t2_1 = new Tag("��ǩ2-1", catalog.get("���2"), catalog.get("���5"));
    Tag t3_1 = new Tag("��ǩ3-1", catalog.get("���3"), catalog.get("���6"));
    Tag t3_2 = new Tag("��ǩ3-2", catalog.get("���3"), catalog.get("���6"));
    Tag t5_1 = new Tag("��ǩ5-1", catalog.get("���5"));
    Tag t6_1 = new Tag("��ǩ6-1", catalog.get("���6"));

    catalog.get("���1").addTag(t1_1);
    catalog.get("���2").addTag(t2_1);
    catalog.get("���3").addTag(t3_1);
    catalog.get("���3").addTag(t3_2);
    catalog.get("���5").addTag(t5_1);
    catalog.get("���6").addTag(t6_1);
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
      Assert.assertEquals(node.getUserObject().toString(), "���" + (i + 1));

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
        Assert.assertEquals(tagNode.getUserObject().toString(), "��ǩ" + (i + 1) + "-" + (j + 1));

        if (i == 1 || i == 2) {
          Assert.assertEquals(1, tagNode.getChildCount());
        } else {
          Assert.assertEquals(0, tagNode.getChildCount());
        }
      }
    }

    // ���2 -> ��ǩ2-1 -> ��ǩ5-1
    DefaultMutableTreeNode node =
        (DefaultMutableTreeNode) root.getChildAt(1).getChildAt(0).getChildAt(0);
    Assert.assertTrue(node.getUserObject() instanceof Tag);
    Assert.assertEquals(node.getUserObject().toString(), "��ǩ5-1");

    // ���3 -> ��ǩ3-1 -> ��ǩ6-1
    node = (DefaultMutableTreeNode) root.getChildAt(2).getChildAt(0).getChildAt(0);
    Assert.assertTrue(node.getUserObject() instanceof Tag);
    Assert.assertEquals(node.getUserObject().toString(), "��ǩ6-1");

    // ���3 -> ��ǩ3-2 -> ��ǩ6-1
    node = (DefaultMutableTreeNode) root.getChildAt(2).getChildAt(1).getChildAt(0);
    Assert.assertTrue(node.getUserObject() instanceof Tag);
    Assert.assertEquals(node.getUserObject().toString(), "��ǩ6-1");
  }

  @Test
  public void getPathFromRootByName() {
    NewsTreeModel model = new NewsTreeModel(catalog);

    TreeNode[] nodes = model.getPathToRoot("���1");
    Assert.assertEquals(2, nodes.length);
    Assert.assertEquals(catalog, ((DefaultMutableTreeNode) nodes[0]).getUserObject());
    Assert.assertEquals("���1", ((DefaultMutableTreeNode) nodes[1]).getUserObject().toString());

    nodes = model.getPathToRoot("��ǩ2-1");
    Assert.assertEquals(3, nodes.length);
    Assert.assertEquals(catalog, ((DefaultMutableTreeNode) nodes[0]).getUserObject());
    Assert.assertEquals("���2", ((DefaultMutableTreeNode) nodes[1]).getUserObject().toString());
    Assert.assertEquals("��ǩ2-1", ((DefaultMutableTreeNode) nodes[2]).getUserObject().toString());

    nodes = model.getPathToRoot("��ǩ3-2");
    Assert.assertEquals(3, nodes.length);
    Assert.assertEquals(catalog, ((DefaultMutableTreeNode) nodes[0]).getUserObject());
    Assert.assertEquals("���3", ((DefaultMutableTreeNode) nodes[1]).getUserObject().toString());
    Assert.assertEquals("��ǩ3-2", ((DefaultMutableTreeNode) nodes[2]).getUserObject().toString());

    // ע�⣺�����ñ�ǩ���������ȥѰ�ң����п������Ƴ����ڶദ�������޷��ҵ���Ҫ��·��
    // FIXME
    nodes = model.getPathToRoot("��ǩ6-1");
    Assert.assertEquals(4, nodes.length);
    Assert.assertEquals(catalog, ((DefaultMutableTreeNode) nodes[0]).getUserObject());
    Assert.assertEquals("���3", ((DefaultMutableTreeNode) nodes[1]).getUserObject().toString());
    Assert.assertEquals("��ǩ3-1", ((DefaultMutableTreeNode) nodes[2]).getUserObject().toString());
  }
}
