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
  public void newsEqualTest() {// News是否相等与ID有关，ruoID相同，则认为是同一条新闻，否则不是
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

    // 光明日报
    news.setUrl("http://epaper.gmw.cn/gmrb/html/2012-10/07/nw.D110000gmrb_20121007_7-01.htm?div=-1");
    result = news.update();
    Assert.assertTrue(result);
    Assert
        .assertEquals(
            news.getContent(),
            "    本报福州10月6日电（记者高建进）福建省第六届“书香八闽”全民读书月暨第七届福州读书月活动今天在福建博物院启动。启动仪式上，有关领导为读书月活动志愿服务队授旗，向福州市10家道德讲堂赠送《福州道德典型》等书籍，向农家书屋、乡镇图书室、县图书馆、中小学图书馆、留守儿童代表捐赠图书，并开启“书香校园”手机阅读平台。本届“书香八闽”全民读书月以“弘扬福建精神，推动科学发展”为主题。活动期间，各地将开展优秀书目推荐、“书香人家”和图书援助等活动，以及“喜迎十八大”百种优秀图书展及精品诵读晚会等。\n");

    // 四川日报
    news.setUrl("http://epaper.scdaily.cn/shtml/scrb/20140729/70638.shtml");
    result = news.update();
    Assert.assertTrue(result);
    Assert
        .assertEquals(
            news.getContent(),
            "    近日，绵阳市平武县旧堡羌族乡庆林村，孩子们在参加西南石油大学记者团组织的“小世界杯”足球赛。庆林村是西南石油大学的大学生社会实践基地之一，每年大学生们定期来到庆林村开展暑期社会实践活动，专门为留守儿童开设暑期课程辅导和安全教育课，并设立贫困儿童奖助学金。 曹正 摄\n");
  }
}
