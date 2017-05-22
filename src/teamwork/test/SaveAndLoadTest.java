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

    // ��NewsCatalog���2�����ţ����ѵ�2��������ӵ�BinCatalog
    // ��ClassCatalog���1����𣬸���������һ����ǩ���ñ�ǩ������ĵ�һ����������
    News news = new News();
    newsCatalog.add(news);
    newsCatalog.add(new News("����1", "", "", "", "", "", ""));
    binCatalog.add(newsCatalog.get(1));

    NewsClass newsClass = new NewsClass("���1");
    Tag tag = new Tag("��ǩ1", newsClass);
    newsClass.addTag(tag);
    classCatalog.add(newsClass);

    news.postTag(tag);

    // ���б������
    String fileName = "the_file_for_save_and_load";
    File file = new File(fileName);
    new SaveControler().save(fileName);

    // ���3��Catalog������
    newsCatalog.remove(1);
    newsCatalog.remove(0);
    classCatalog.remove(0);
    binCatalog.remove(0);

    // ���ж�ȡ����
    new LoadControler().load(fileName);

    Assert.assertEquals(2, newsCatalog.size());
    Assert.assertEquals(1, classCatalog.size());
    Assert.assertEquals(1, binCatalog.size());

    Assert.assertEquals(binCatalog.get(0), newsCatalog.get(1));
    Assert.assertEquals(1, newsCatalog.get(0).sizeTag());
    Assert.assertEquals(1, classCatalog.get(0).sizeTag());
    Assert.assertEquals(newsCatalog.get(0), classCatalog.get(0).getTag(0).getNewsList().get(0));

    //���3��Catalog
    newsCatalog.remove(1);
    newsCatalog.remove(0);
    classCatalog.remove(0);
    binCatalog.remove(0);
    file.delete();
  }
}
