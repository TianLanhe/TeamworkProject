package teamwork.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import teamwork.model.BinCatalog;
import teamwork.model.News;

public class BinCatalogTest {

  private static BinCatalog catalog;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    catalog = BinCatalog.getInstance();
  }

  @Test
  public void newsOperationTest() {
    Assert.assertEquals(0, catalog.size());// size()

    catalog.add(new News());// addNews()
    Assert.assertEquals(1, catalog.size());

    News news = new News();
    news.setId("new_id");
    Assert.assertFalse(catalog.contains(news));// containsNews()

    catalog.add(news);
    Assert.assertEquals(2, catalog.size());
    Assert.assertTrue(catalog.contains(news));

    catalog.add(news);
    Assert.assertEquals(2, catalog.size());// addNews()

    Assert.assertEquals(0, catalog.indexOf(new News()));// indexOfNews()
    Assert.assertEquals(1, catalog.indexOf(news));

    catalog.remove(0);// removeNews()
    Assert.assertEquals(1, catalog.size());

    catalog.remove(news);
    Assert.assertEquals(0, catalog.size());
  }

}
