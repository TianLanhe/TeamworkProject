package teamwork.test;

import org.junit.Assert;
import org.junit.Test;

import teamwork.model.News;
import teamwork.model.NewsClass;
import teamwork.model.Tag;

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

  @Test
  public void postAndRemoveTagTest() {
    News news =
        new News("newsId", "newsTitle", "newsContent", "1996-12-23", "newsLocation", "newsType",
            "www.baidu.com");

    Assert.assertEquals(news.sizeTag(), 0);

    Tag tag = new Tag("��ǩ1");
    news.postTag(tag);
    Assert.assertEquals(news.sizeTag(), 1);

    tag = new Tag("��ǩ2");
    news.postTag(tag);
    Assert.assertEquals(news.sizeTag(), 2);

    // ��ǩ�����ظ�ճ����ֻ��ճ��һ��
    tag = new Tag("��ǩ1");
    news.postTag(tag);
    Assert.assertEquals(news.sizeTag(), 2);

    news.removeTag(0);
    Assert.assertEquals(news.sizeTag(), 1);

    news.removeTag("��ǩ2");
    Assert.assertEquals(news.sizeTag(), 0);
  }

  @Test
  public void getTagTest() {
    News news =
        new News("newsId", "newsTitle", "newsContent", "1996-12-23", "newsLocation", "newsType",
            "www.baidu.com");

    Tag tag = new Tag("��ǩ1");
    news.postTag(tag);

    Assert.assertEquals(null, news.getTag("�����ڵı�ǩ"));
    Assert.assertEquals(tag, news.getTag(0));
    Assert.assertEquals(tag, news.getTag("��ǩ1"));

    tag = new Tag("��ǩ2");
    news.postTag(tag);

    Assert.assertEquals(0, news.indexOfTag("��ǩ1"));
    Assert.assertEquals(1, news.indexOfTag("��ǩ2"));
    Assert.assertEquals(-1, news.indexOfTag("�����ڵı�ǩ"));
  }
  
  @Test
  public void hasTagOrClassTest(){
    News news =
        new News("newsId", "newsTitle", "newsContent", "1996-12-23", "newsLocation", "newsType",
            "www.baidu.com");

    NewsClass c1 = new NewsClass("���1");
    Tag t1 = new Tag("��ǩ1");
    c1.addTag(t1);
    
    NewsClass c2 = new NewsClass("���2");
    Tag t2 = new Tag("��ǩ2");
    c2.addTag(t2);
    
    Assert.assertFalse(news.hasClass(c1));
    Assert.assertFalse(news.hasClass(c2));
    Assert.assertFalse(news.hasTag(t1));
    Assert.assertFalse(news.hasTag(t2));
    Assert.assertFalse(news.hasTag("��ǩ1"));
    Assert.assertFalse(news.hasTag("��ǩ2"));
    
    news.postTag(t1);
    
    Assert.assertTrue(news.hasClass(c1));
    Assert.assertFalse(news.hasClass(c2));
    Assert.assertTrue(news.hasTag(t1));
    Assert.assertFalse(news.hasTag(t2));
    Assert.assertTrue(news.hasTag("��ǩ1"));
    Assert.assertFalse(news.hasTag("��ǩ2"));
    
    news.postTag(t2);
    
    Assert.assertTrue(news.hasClass(c1));
    Assert.assertTrue(news.hasClass(c2));
    Assert.assertTrue(news.hasTag(t1));
    Assert.assertTrue(news.hasTag(t2));
    Assert.assertTrue(news.hasTag("��ǩ1"));
    Assert.assertTrue(news.hasTag("��ǩ2"));
    
    news.removeTag("��ǩ2");
    
    Assert.assertTrue(news.hasClass(c1));
    Assert.assertFalse(news.hasClass(c2));
    Assert.assertTrue(news.hasTag(t1));
    Assert.assertFalse(news.hasTag(t2));
    Assert.assertTrue(news.hasTag("��ǩ1"));
    Assert.assertFalse(news.hasTag("��ǩ2"));
  }
}
