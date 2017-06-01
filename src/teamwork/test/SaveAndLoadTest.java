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
    // ��ClassCatalog���3��������е�2������Ƕ�ѡ��ÿ����������1����ǩ
    // ����������ǰ2����ǩ�����е�3����ǩ��������2�����
    News news = new News("����1", "", "", "", "", "", "");
    newsCatalog.add(news);
    newsCatalog.add(new News("����2", "", "", "", "", "", ""));

    // ��һ�����һ����ǩ����ѡ������ǩ
    NewsClass newsClass = new NewsClass("���1");
    Tag tag = new Tag("��ǩ1", newsClass);
    newsClass.addTag(tag);
    classCatalog.add(newsClass);

    news.postTag(tag);

    // �ڶ������һ����ǩ����ѡ������ǩ
    newsClass = new NewsClass("���2", true);
    tag = new Tag("��ǩ2", newsClass);
    newsClass.addTag(tag);
    classCatalog.add(newsClass);

    news.postTag(tag);

    // ���������һ����ǩ�����������ڶ������
    newsClass = new NewsClass("���3");
    tag = new Tag("��ǩ3", newsClass, classCatalog.get("���2"));
    newsClass.addTag(tag);
    classCatalog.add(newsClass);

    // ɾ���ڶ�������
    binCatalog.add(newsCatalog.get(1));

    // ���б������
    String fileName = "the_file_for_save_and_load";
    File file = new File(fileName);
    new SaveControler().save(fileName);

    // ���3��Catalog������
    newsCatalog.remove(1);
    newsCatalog.remove(0);
    classCatalog.remove(2);
    classCatalog.remove(1);
    classCatalog.remove(0);
    binCatalog.remove(0);

    // ���ж�ȡ����
    new LoadControler().load(fileName);

    Assert.assertEquals(2, newsCatalog.size());
    Assert.assertEquals(3, classCatalog.size());
    Assert.assertEquals(1, binCatalog.size());

    Assert.assertEquals(binCatalog.get(0), newsCatalog.get(1));
    Assert.assertEquals(2, newsCatalog.get(0).sizeTag());
    Assert.assertEquals(0, newsCatalog.get(1).sizeTag());
    for (NewsClass c : classCatalog.getClassList()) {
      Assert.assertEquals(1, c.sizeTag());
      if (c.getName().equals("���2"))
        Assert.assertTrue(c.isMultChoice());
      else
        Assert.assertFalse(c.isMultChoice());
    }
    Assert.assertFalse(classCatalog.get(0).getTag(0).hasNextClass());
    Assert.assertFalse(classCatalog.get(1).getTag(0).hasNextClass());
    Assert.assertTrue(classCatalog.get(2).getTag(0).hasNextClass());
    Assert.assertEquals(classCatalog.get(1), classCatalog.get(2).getTag(0).getNextClass());
    Assert.assertEquals(newsCatalog.get(0), classCatalog.get(0).getTag(0).getNewsList().get(0));

    // ���3��Catalog
    newsCatalog.remove(1);
    newsCatalog.remove(0);
    classCatalog.remove(2);
    classCatalog.remove(1);
    classCatalog.remove(0);
    binCatalog.remove(0);
    file.delete();
  }
}
