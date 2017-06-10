package teamwork.test;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import teamwork.model.ClassCatalog;
import teamwork.model.News;
import teamwork.model.NewsClass;
import teamwork.model.Tag;

public class TagTest {
  
  private static NewsClass newsClass;
  private static ClassCatalog catalog;
  
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    catalog = ClassCatalog.getInstance();
    newsClass = new NewsClass("this is a class");
    catalog.add(newsClass);
  }
  
  @AfterClass
  public static void afterClass() throws Exception{
    catalog.remove(newsClass);
  }

  @Test
  public void tagNoNextClassTest() {
    Tag tag = new Tag("this is a tag",newsClass);
    Assert.assertEquals(null, tag.getNextClass());
    Assert.assertFalse(tag.hasNextClass());
  }

  @Test
  public void tagHasNextClassTest() {
    NewsClass c = new NewsClass("this is a news class");
    catalog.add(c);
    Tag tag = new Tag("this is a tag",newsClass,c);
    Assert.assertEquals(c, tag.getNextClass());
    Assert.assertTrue(tag.hasNextClass());
    catalog.remove(c);
  }

  @Test
  public void newsOperationTest() {
    Tag tag = new Tag("this is a tag", newsClass);
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
    Tag tag1 = new Tag("this is a tag",newsClass);
    Tag tag2 = new Tag("this is a tag",newsClass);
    Tag tag3 = new Tag("this is an another tag",newsClass);
    Tag tag4 = new Tag("this is a tag",new NewsClass(""));

    Assert.assertEquals(tag1, tag1);
    Assert.assertEquals(tag1, tag2);
    Assert.assertNotEquals(tag1, tag3);
    Assert.assertNotEquals(tag1, tag4);
  }

  @Test
  public void tagFindNewsTest() {
    Tag tag = new Tag("this is a tag",newsClass);

    News news1 = new News();
    news1.setId("新闻1");
    tag.addNews(news1);

    News news2 = new News();
    news2.setId("新闻2");
    tag.addNews(news2);

    News news3 = new News();
    news3.setId("新闻3");
    tag.addNews(news3);

    Assert.assertEquals(news1, tag.findPrevNews(news2));
    Assert.assertEquals(news3, tag.findNextNews(news2));

    Assert.assertEquals(news1, tag.findPrevNews(news1));
    Assert.assertEquals(news2, tag.findNextNews(news1));

    Assert.assertEquals(news2, tag.findPrevNews(news3));
    Assert.assertEquals(news3, tag.findNextNews(news3));
  }

}
