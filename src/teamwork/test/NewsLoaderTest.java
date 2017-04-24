package teamwork.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import teamwork.loader.NewsLoader;
import teamwork.model.News;

public class NewsLoaderTest {

  private static NewsLoader newsLoader;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    newsLoader = new NewsLoader();
  }

  @Test
  public void initTest() {
    NewsLoader newsLoader2 = new NewsLoader();
    Assert.assertFalse(newsLoader2.hasNext());
    Assert.assertEquals(newsLoader2.next(), null);

    Assert.assertFalse(newsLoader2.loadFrom("abcde"));
    Assert.assertFalse(newsLoader2.hasNext());
    Assert.assertEquals(newsLoader2.next(), null);
  }

  @Test
  public void guangmingTest() {
    newsLoader.loadFrom("D:\\Eclipse\\TeamworkProject\\guangming.xml");
    Assert.assertTrue(newsLoader.hasNext());

    News news = newsLoader.next();
    Assert.assertNotEquals(null, news);

    Assert.assertEquals("青少年“身边最让我感动的人”评选揭晓", news.getTitle());
    Assert.assertEquals("2006-01-16", news.getDate());
    Assert.assertEquals("光明日报", news.getLocation());
    Assert.assertEquals("news:23lh^200601161410077(S:193916305)", news.getId());
    Assert.assertEquals("02,教科文衞", news.getType());
    Assert.assertEquals("", news.getUrl());
  }

  @Test
  public void sichuanTest() {
    newsLoader.loadFrom("D:\\Eclipse\\TeamworkProject\\sichuan.xml");
    Assert.assertTrue(newsLoader.hasNext());

    News news = newsLoader.next();
    Assert.assertNotEquals(null, news);

    Assert.assertEquals("我省检察机关首次公布未成年人保护典型案例", news.getTitle());
    Assert.assertEquals("2015-12-29", news.getDate());
    Assert.assertEquals("四川日报(数字报)", news.getLocation());
    Assert.assertEquals("news:15jl^201512293153338(S:196124888)", news.getId());
    Assert.assertEquals("11,时政新闻?社会新闻", news.getType());
    Assert.assertEquals("http://epaper.scdaily.cn/shtml/scrb/20151229/119905.shtml", news.getUrl());
  }

  @Test
  public void nanfangTest() {
    newsLoader.loadFrom("D:\\Eclipse\\TeamworkProject\\nanfangdaily.xml");
    Assert.assertTrue(newsLoader.hasNext());

    News news = newsLoader.next();
    Assert.assertNotEquals(null, news);

    Assert.assertEquals("关爱留守儿童宝马车主捐赠图书1.4万余册", news.getTitle());
    Assert.assertEquals("2010-12-30", news.getDate());
    Assert.assertEquals("南方都市报(全国版)", news.getLocation());
    Assert.assertEquals("news:06cj^201012305333859(S:193625388)", news.getId());
    Assert.assertEquals("CⅡ29,黄金车市每周调查,车市简...", news.getType());
    Assert.assertEquals("", news.getUrl());
  }
}
