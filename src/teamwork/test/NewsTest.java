package teamwork.test;

import org.junit.Assert;
import org.junit.Test;

import teamwork.model.News;

public class NewsTest {

  @Test
  public void newsGetMember() {
    News news =
        new News("newsId", "newsTitle", "newsContent", "1996-12-23", "newsLocation", "newsType",
            "www.baidu.com");
    Assert.assertEquals(news.getContent(), "newsContent");
    Assert.assertEquals(news.getTitle(), "newsTitle");
    Assert.assertEquals(news.getId(), "newsId");
    Assert.assertEquals(news.getLocation(), "newsLocation");
    Assert.assertEquals(news.getType(), "newsType");
    Assert.assertEquals(news.getUrl(), "www.baidu.com");
    Assert.assertEquals(news.getDate(), "1996-12-23");
  }

  @Test
  public void newsEqualTest() {// News�Ƿ������ID�йأ�ruoID��ͬ������Ϊ��ͬһ�����ţ�������
    News news =
        new News("newsId", "newsTitle", "newsContent", "1996-12-23", "newsLocation", "newsType",
            "www.baidu.com");

    Assert.assertEquals(news, news);

    News news2 = new News();
    news2.setId("newsId");
    Assert.assertEquals(news, news2);

    news2.setId("another_newsId");
    Assert.assertNotEquals(news, news2);
  }

  @Test
  public void newsUpdateTest() {
    News news =
        new News("newsId", "newsTitle", "newsContent", "1996-12-23", "newsLocation", "newsType",
            "www.baidu.com");

    news.setUrl("http://abc/");
    boolean result = news.update();
    Assert.assertFalse(result);

    // �����ձ�
    news.setUrl("http://epaper.gmw.cn/gmrb/html/2012-10/07/nw.D110000gmrb_20121007_7-01.htm?div=-1");
    result = news.update();
    Assert.assertTrue(result);
    Assert
        .assertEquals(
            news.getContent(),
            "    ��������10��6�յ磨���߸߽���������ʡ�����조���������ȫ��������ߵ��߽츣�ݶ����»�����ڸ�������Ժ������������ʽ�ϣ��й��쵼Ϊ�����»־Ը��������죬������10�ҵ��½������͡����ݵ��µ��͡����鼮����ũ�����ݡ�����ͼ���ҡ���ͼ��ݡ���Сѧͼ��ݡ����ض�ͯ�������ͼ�飬������������У԰���ֻ��Ķ�ƽ̨�����조���������ȫ��������ԡ����︣�������ƶ���ѧ��չ��Ϊ���⡣��ڼ䣬���ؽ���չ������Ŀ�Ƽ����������˼ҡ���ͼ��Ԯ���Ȼ���Լ���ϲӭʮ�˴󡱰�������ͼ��չ����Ʒ�ж����ȡ�\n");

    // �Ĵ��ձ�
    news.setUrl("http://epaper.scdaily.cn/shtml/scrb/20140729/70638.shtml");
    result = news.update();
    Assert.assertTrue(result);
    Assert
        .assertEquals(
            news.getContent(),
            "    ���գ�������ƽ���ؾɱ�Ǽ�������ִ壬�������ڲμ�����ʯ�ʹ�ѧ��������֯�ġ�С���籭�������������ִ�������ʯ�ʹ�ѧ�Ĵ�ѧ�����ʵ������֮һ��ÿ���ѧ���Ƕ����������ִ忪չ�������ʵ�����ר��Ϊ���ض�ͯ�������ڿγ̸����Ͱ�ȫ�����Σ�������ƶ����ͯ����ѧ�� ���� ��\n");
  }
}
