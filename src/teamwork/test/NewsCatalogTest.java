package teamwork.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import teamwork.model.News;
import teamwork.model.NewsCatalog;

public class NewsCatalogTest {

  private static NewsCatalog catalog;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    catalog = NewsCatalog.getInstance();
  }

  @Test
  public void newsOperationTest() {
    Assert.assertEquals(0, catalog.size());// size()

    catalog.add(new News());// addNews()
    Assert.assertEquals(1, catalog.size());

    News news = new News();
    news.setId("new_id");
    Assert.assertFalse(catalog.contains(news));// containsNews()
    Assert.assertFalse(catalog.contains(news.getId()));// containsNews()

    catalog.add(news);
    Assert.assertEquals(2, catalog.size());
    Assert.assertTrue(catalog.contains(news));
    Assert.assertTrue(catalog.contains(news.getId()));

    catalog.add(news);
    Assert.assertEquals(2, catalog.size());// addNews()

    Assert.assertEquals(0, catalog.indexOf(new News()));// indexOfNews()
    Assert.assertEquals(0, catalog.indexOf(""));
    Assert.assertEquals(1, catalog.indexOf(news));
    Assert.assertEquals(1, catalog.indexOf(news.getId()));

    catalog.remove(0);// removeNews()
    Assert.assertEquals(1, catalog.size());

    catalog.remove(news.getId());
    Assert.assertEquals(0, catalog.size());
  }

  @Test
  public void updateNewsTest() {
    News news = new News();
    news.setId("新闻1");
    news.setUrl("http://abc/");
    catalog.add(news);

    news = new News();
    news.setId("新闻2");
    news.setUrl("http://epaper.gmw.cn/gmrb/html/2012-10/07/nw.D110000gmrb_20121007_7-01.htm?div=-1");
    catalog.add(news);

    news = new News();
    news.setId("新闻3");
    news.setUrl("http://epaper.scdaily.cn/shtml/scrb/20151229/119905.shtml");
    catalog.add(news);

    int count = catalog.updateAll();
    Assert.assertEquals(2, count);

    catalog.remove(0);
    catalog.remove(0);
    catalog.remove(0);
    Assert.assertEquals(0, catalog.size());
  }
}
