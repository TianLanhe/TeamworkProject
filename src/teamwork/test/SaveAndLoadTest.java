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
    // 往ClassCatalog添加1个类别，该类别添加了一条标签，该标签被上面的第一条新闻贴了
    News news = new News();
    newsCatalog.add(news);
    newsCatalog.add(new News("新闻1", "", "", "", "", "", ""));
    binCatalog.add(newsCatalog.get(1));

    NewsClass newsClass = new NewsClass("类别1");
    Tag tag = new Tag("标签1", newsClass);
    newsClass.addTag(tag);
    classCatalog.add(newsClass);

    news.postTag(tag);

    // 进行保存操作
    String fileName = "the_file_for_save_and_load";
    File file = new File(fileName);
    new SaveControler().save(fileName);

    // 清空3个Catalog的内容
    newsCatalog.remove(1);
    newsCatalog.remove(0);
    classCatalog.remove(0);
    binCatalog.remove(0);

    // 进行读取操作
    new LoadControler().load(fileName);

    Assert.assertEquals(2, newsCatalog.size());
    Assert.assertEquals(1, classCatalog.size());
    Assert.assertEquals(1, binCatalog.size());

    Assert.assertEquals(binCatalog.get(0), newsCatalog.get(1));
    Assert.assertEquals(1, newsCatalog.get(0).sizeTag());
    Assert.assertEquals(1, classCatalog.get(0).sizeTag());
    Assert.assertEquals(newsCatalog.get(0), classCatalog.get(0).getTag(0).getNewsList().get(0));

    //清空3个Catalog
    newsCatalog.remove(1);
    newsCatalog.remove(0);
    classCatalog.remove(0);
    binCatalog.remove(0);
    file.delete();
  }
}
