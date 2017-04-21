package teamwork.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import teamwork.model.News;

public class NewsTest {

  private static News news;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    news = new News("newsId","newsTitle","newsContent","1996-12-23","newsLocation","newsType","www.baidu.com");
  }
  
  @Test
  public void newsGetMember(){
    Assert.assertEquals(news.getContent(), "newsContent");
    Assert.assertEquals(news.getTitle(), "newsTitle");
    Assert.assertEquals(news.getId(), "newsId");
    Assert.assertEquals(news.getLocation(), "newsLocation");
    Assert.assertEquals(news.getType(), "newsType");
    Assert.assertEquals(news.getUrl(), "www.baidu.com");
    Assert.assertEquals(news.getDate(), "1996-12-23");
  }
  
  @Test
  public void newsEqualTest(){
    News news2 = new News("newsId","","","","","","");
    Assert.assertTrue(news.equals(news2));
    
    news2.setId("another_newsId");
    Assert.assertFalse(news.equals(news2));
  }
  
}
