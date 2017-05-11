package teamwork.test;

import org.junit.Assert;
import org.junit.Test;

import teamwork.model.News;
import teamwork.model.NewsClass;
import teamwork.model.Tag;

public class PostTagTest {

  @Test
  public void postSingleTagTest() {
    News news =
        new News("newsId", "newsTitle", "newsContent", "1996-12-23", "newsLocation", "newsType",
            "www.baidu.com");

    Assert.assertEquals(news.sizeTag(), 0);

    NewsClass c_single = new NewsClass("单选类别");
    Tag tag1 = new Tag("标签1", c_single);
    Tag tag2 = new Tag("标签2", c_single);
    c_single.addTag(tag1);
    c_single.addTag(tag2);

    Assert.assertFalse(news.hasClass(c_single));
    Assert.assertFalse(news.hasTag(tag1));
    Assert.assertFalse(news.hasTag(tag2));

    news.postTag(tag1);
    Assert.assertEquals(news.sizeTag(), 1);

    Assert.assertTrue(news.hasClass(c_single));
    Assert.assertTrue(news.hasTag(tag1));
    Assert.assertFalse(news.hasTag(tag2));

    news.postTag(tag2);
    Assert.assertEquals(news.sizeTag(), 1);

    Assert.assertTrue(news.hasClass(c_single));
    Assert.assertFalse(news.hasTag(tag1));
    Assert.assertTrue(news.hasTag(tag2));
  }

  @Test
  public void postMultTagTest() {
    News news =
        new News("newsId", "newsTitle", "newsContent", "1996-12-23", "newsLocation", "newsType",
            "www.baidu.com");

    Assert.assertEquals(news.sizeTag(), 0);

    NewsClass c_mult = new NewsClass("多选类别", true);

    Tag tag1 = new Tag("标签1", c_mult);
    Tag tag2 = new Tag("标签2", c_mult);
    c_mult.addTag(tag1);
    c_mult.addTag(tag2);

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

    // 不能重复贴标签
    news.postTag(tag1);
    Assert.assertEquals(news.sizeTag(), 2);
  }
}
