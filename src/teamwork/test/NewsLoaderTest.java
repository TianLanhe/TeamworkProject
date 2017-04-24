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

    Assert.assertEquals("�����ꡰ��������Ҹж����ˡ���ѡ����", news.getTitle());
    Assert.assertEquals("2006-01-16", news.getDate());
    Assert.assertEquals("�����ձ�", news.getLocation());
    Assert.assertEquals("news:23lh^200601161410077(S:193916305)", news.getId());
    Assert.assertEquals("02,�̿����o", news.getType());
    Assert.assertEquals("", news.getUrl());
  }

  @Test
  public void sichuanTest() {
    newsLoader.loadFrom("D:\\Eclipse\\TeamworkProject\\sichuan.xml");
    Assert.assertTrue(newsLoader.hasNext());

    News news = newsLoader.next();
    Assert.assertNotEquals(null, news);

    Assert.assertEquals("��ʡ�������״ι���δ�����˱������Ͱ���", news.getTitle());
    Assert.assertEquals("2015-12-29", news.getDate());
    Assert.assertEquals("�Ĵ��ձ�(���ֱ�)", news.getLocation());
    Assert.assertEquals("news:15jl^201512293153338(S:196124888)", news.getId());
    Assert.assertEquals("11,ʱ������?�������", news.getType());
    Assert.assertEquals("http://epaper.scdaily.cn/shtml/scrb/20151229/119905.shtml", news.getUrl());
  }

  @Test
  public void nanfangTest() {
    newsLoader.loadFrom("D:\\Eclipse\\TeamworkProject\\nanfangdaily.xml");
    Assert.assertTrue(newsLoader.hasNext());

    News news = newsLoader.next();
    Assert.assertNotEquals(null, news);

    Assert.assertEquals("�ذ����ض�ͯ����������ͼ��1.4�����", news.getTitle());
    Assert.assertEquals("2010-12-30", news.getDate());
    Assert.assertEquals("�Ϸ����б�(ȫ����)", news.getLocation());
    Assert.assertEquals("news:06cj^201012305333859(S:193625388)", news.getId());
    Assert.assertEquals("C��29,�ƽ���ÿ�ܵ���,���м�...", news.getType());
    Assert.assertEquals("", news.getUrl());
  }
}
