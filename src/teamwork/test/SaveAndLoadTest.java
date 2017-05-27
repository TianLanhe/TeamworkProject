package teamwork.test;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import teamwork.model.BinCatalog;
import teamwork.model.ClassCatalog;
import teamwork.model.News;
import teamwork.model.NewsCatalog;
import teamwork.model.NewsClass;
import teamwork.model.Tag;
import teamwork.model.controler.LoadControler;
import teamwork.model.controler.SaveControler;

public class SaveAndLoadTest {

  @Test
  public void saveAndLoadTest() {
    NewsCatalog newsCatalog = NewsCatalog.getInstance();
    ClassCatalog classCatalog = ClassCatalog.getInstance();
    BinCatalog binCatalog = BinCatalog.getInstance();

    // 往NewsCatalog添加2条新闻，并把第2条新闻添加到BinCatalog
    // 往ClassCatalog添加3个类别，其中第2个类别是多选。每个类别添加了1条标签
    // 新闻贴上了前2条标签，其中第3条标签关联到第2个类别
    News news = new News("新闻1", "", "", "", "", "", "");
    newsCatalog.add(news);
    newsCatalog.add(new News("新闻2", "", "", "", "", "", ""));

    // 第一个类别，一个标签，单选，贴标签
    NewsClass newsClass = new NewsClass("类别1");
    Tag tag = new Tag("标签1", newsClass);
    newsClass.addTag(tag);
    classCatalog.add(newsClass);

    news.postTag(tag);

    // 第二个类别，一个标签，多选，贴标签
    newsClass = new NewsClass("类别2", true);
    tag = new Tag("标签2", newsClass);
    newsClass.addTag(tag);
    classCatalog.add(newsClass);

    news.postTag(tag);

    // 第三个类别，一个标签，关联到到第二个类别
    newsClass = new NewsClass("类别3");
    tag = new Tag("标签3", newsClass, classCatalog.get("类别2"));
    newsClass.addTag(tag);
    classCatalog.add(newsClass);

    // 删除第二条新闻
    binCatalog.add(newsCatalog.get(1));

    // 进行保存操作
    String fileName = "the_file_for_save_and_load";
    File file = new File(fileName);
    new SaveControler().save(fileName);

    // 清空3个Catalog的内容
    newsCatalog.remove(1);
    newsCatalog.remove(0);
    classCatalog.remove(2);
    classCatalog.remove(1);
    classCatalog.remove(0);
    binCatalog.remove(0);

    // 进行读取操作
    new LoadControler().load(fileName);

    Assert.assertEquals(2, newsCatalog.size());
    Assert.assertEquals(3, classCatalog.size());
    Assert.assertEquals(1, binCatalog.size());

    Assert.assertEquals(binCatalog.get(0), newsCatalog.get(1));
    Assert.assertEquals(2, newsCatalog.get(0).sizeTag());
    Assert.assertEquals(0, newsCatalog.get(1).sizeTag());
    for (NewsClass c : classCatalog.getClassList()) {
      Assert.assertEquals(1, c.sizeTag());
      if (c.getName().equals("类别2"))
        Assert.assertTrue(c.isMultChoice());
      else
        Assert.assertFalse(c.isMultChoice());
    }
    Assert.assertFalse(classCatalog.get(0).getTag(0).hasNextClass());
    Assert.assertFalse(classCatalog.get(1).getTag(0).hasNextClass());
    Assert.assertTrue(classCatalog.get(2).getTag(0).hasNextClass());
    Assert.assertEquals(classCatalog.get(1), classCatalog.get(2).getTag(0).getNextClass());
    Assert.assertEquals(newsCatalog.get(0), classCatalog.get(0).getTag(0).getNewsList().get(0));

    // 清空3个Catalog
    newsCatalog.remove(1);
    newsCatalog.remove(0);
    classCatalog.remove(2);
    classCatalog.remove(1);
    classCatalog.remove(0);
    binCatalog.remove(0);
    file.delete();
  }
}
