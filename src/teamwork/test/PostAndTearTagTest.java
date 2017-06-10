package teamwork.test;

import org.junit.Assert;
import org.junit.Test;

import teamwork.model.ClassCatalog;
import teamwork.model.News;
import teamwork.model.NewsClass;
import teamwork.model.Tag;

public class PostAndTearTagTest {

  @Test
  public void postAndTearSingleTagTest() {
    News news =
        new News("newsId", "newsTitle", "newsContent", "1996-12-23", "newsLocation", "newsType",
            "www.baidu.com");

    Assert.assertEquals(news.sizeTag(), 0);

    ClassCatalog catalog = ClassCatalog.getInstance();
    NewsClass c_single = new NewsClass("��ѡ���");
    
    Tag tag1 = new Tag("��ǩ1", c_single);
    Tag tag2 = new Tag("��ǩ2", c_single);
    c_single.addTag(tag1);
    c_single.addTag(tag2);
    catalog.add(c_single);

    Assert.assertFalse(news.hasClass(c_single));
    Assert.assertFalse(news.hasTag(tag1));
    Assert.assertFalse(news.hasTag(tag2));

    // ����"��ǩ1"
    news.postTag(tag1);
    Assert.assertEquals(news.sizeTag(), 1);

    Assert.assertTrue(news.hasClass(c_single));
    Assert.assertTrue(news.hasTag(tag1));
    Assert.assertFalse(news.hasTag(tag2));

    // ���� "��ǩ2" ��� "��ǩ1"
    news.postTag(tag2);
    Assert.assertEquals(news.sizeTag(), 1);

    Assert.assertTrue(news.hasClass(c_single));
    Assert.assertFalse(news.hasTag(tag1));
    Assert.assertTrue(news.hasTag(tag2));

    // ˺ȥ��ǩ
    boolean flag = news.tearTag(tag2);
    Assert.assertTrue(flag);
    Assert.assertFalse(news.hasClass(c_single));
    Assert.assertFalse(news.hasTag(tag1));
    Assert.assertFalse(news.hasTag(tag2));
    Assert.assertEquals(news.sizeTag(), 0);

    flag = news.tearTag(tag1);
    Assert.assertFalse(flag);
    
    catalog.remove(c_single);
  }

  @Test
  public void postAndTearMultTagTest() {
    News news =
        new News("newsId", "newsTitle", "newsContent", "1996-12-23", "newsLocation", "newsType",
            "www.baidu.com");

    Assert.assertEquals(news.sizeTag(), 0);

    ClassCatalog catalog = ClassCatalog.getInstance();
    NewsClass c_mult = new NewsClass("��ѡ���", true);

    Tag tag1 = new Tag("��ǩ1", c_mult);
    Tag tag2 = new Tag("��ǩ2", c_mult);
    c_mult.addTag(tag1);
    c_mult.addTag(tag2);
    catalog.add(c_mult);

    Assert.assertFalse(news.hasClass(c_mult));
    Assert.assertFalse(news.hasTag(tag1));
    Assert.assertFalse(news.hasTag(tag2));

    news.postTag(tag1);
    Assert.assertEquals(news.sizeTag(), 1);

    Assert.assertTrue(news.hasClass(c_mult));
    Assert.assertTrue(news.hasTag(tag1));
    Assert.assertFalse(news.hasTag(tag2));

    news.postTag(tag2);
    Assert.assertEquals(news.sizeTag(), 2);

    Assert.assertTrue(news.hasClass(c_mult));
    Assert.assertTrue(news.hasTag(tag1));
    Assert.assertTrue(news.hasTag(tag2));

    // �����ظ�����ǩ
    news.postTag(tag1);
    Assert.assertEquals(news.sizeTag(), 2);

    // ˺ȥ��ǩ
    boolean flag = news.tearTag(tag2);
    Assert.assertTrue(flag);
    Assert.assertTrue(news.hasClass(c_mult));
    Assert.assertTrue(news.hasTag(tag1));
    Assert.assertFalse(news.hasTag(tag2));
    Assert.assertEquals(news.sizeTag(), 1);

    flag = news.tearTag(tag1);
    Assert.assertTrue(flag);
    Assert.assertFalse(news.hasClass(c_mult));
    Assert.assertFalse(news.hasTag(tag1));
    Assert.assertFalse(news.hasTag(tag2));
    Assert.assertEquals(news.sizeTag(), 0);

    flag = news.tearTag(tag1);
    Assert.assertFalse(flag);
    
    catalog.remove(c_mult);
  }
}
