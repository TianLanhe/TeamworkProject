package teamwork.test;

import org.junit.Assert;
import org.junit.Test;

import teamwork.model.News;
import teamwork.model.NewsClass;
import teamwork.model.Tag;

public class TagTest {

  @Test
  public void tagNullNextClassTest() {
    Tag tag = new Tag("this is a tag");
    Assert.assertEquals(null, tag.getNextClass());
    Assert.assertFalse(tag.hasNextClass());
  }

  @Test
  public void tagHasNextClassTest() {
    NewsClass c = new NewsClass("this is a news class");
    Tag tag = new Tag("this is a tag", c);
    Assert.assertEquals(c, tag.getNextClass());
    Assert.assertTrue(tag.hasNextClass());
  }

  @Test
  public void newsOperationTest() {
    Tag tag = new Tag("this is a tag");
    Assert.assertEquals(0, tag.newsSize());// size()

    tag.addNews(new News());// addNews()
    Assert.assertEquals(1, tag.newsSize());

    News news = new News();
    news.setId("new_id");
    Assert.assertFalse(tag.contains(news));// containsNews()

    tag.addNews(news);
    Assert.assertEquals(2, tag.newsSize());
    Assert.assertTrue(tag.contains(news));

    tag.addNews(news);
    Assert.assertEquals(2, tag.newsSize());// addNews()

    Assert.assertEquals(0, tag.indexOfNews(new News()));// indexOfNews()
    Assert.assertEquals(1, tag.indexOfNews(news));

    tag.removeNews(0);// removeNews()
    Assert.assertEquals(1, tag.newsSize());

    tag.removeNews(news);
    Assert.assertEquals(0, tag.newsSize());
  }

  @Test
  public void tagEqualTest() {
    Tag tag1 = new Tag("this is a tag");
    Tag tag2 = new Tag("this is a tag");
    Tag tag3 = new Tag("this is an another tag");

    Assert.assertEquals(tag1, tag1);
    Assert.assertEquals(tag1, tag2);
    Assert.assertNotEquals(tag1, tag3);
  }

}
