package teamwork.test;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import teamwork.model.ClassCatalog;
import teamwork.model.NewsClass;
import teamwork.model.Tag;


public class ClassCatalogTest {
  private static ClassCatalog catalog;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    catalog = ClassCatalog.getInstance();
  }
  
  @Test
  public void classOperationTest(){
    Assert.assertEquals(0, catalog.size());
    
    catalog.add(new NewsClass("类别1"));
    Assert.assertEquals(1, catalog.size());
    
    NewsClass c = new NewsClass("类别2");
    Assert.assertFalse(catalog.contains(c));
    
    catalog.add(c);
    Assert.assertEquals(2, catalog.size());
    Assert.assertTrue(catalog.contains(c));
    
    catalog.add(c);
    Assert.assertEquals(2, catalog.size());
    
    Assert.assertEquals(1,catalog.indexOf(c));
    Assert.assertEquals(0,catalog.indexOf("类别1"));
    
    catalog.remove(0);
    Assert.assertEquals(1, catalog.size());
    
    catalog.remove("类别2");
    Assert.assertEquals(0, catalog.size());
  }
  
  @Test
  public void getSpecialClassTest(){
    Assert.assertEquals(0, catalog.size());
    Assert.assertEquals(0, catalog.getClassNotRelatedToTag().size());
    Assert.assertEquals(0, catalog.getClassRelatedToTag().size());
    
    NewsClass c = new NewsClass("被关联的类");
    catalog.add(c);
    
    NewsClass c2 = new NewsClass("无关联的类");
    catalog.add(c2);
    
    //设置关联
    Tag tag = new Tag("标签",c2,c); //c2->tag->c
    c2.addTag(tag);
    
    Assert.assertEquals(1, catalog.getClassNotRelatedToTag().size());
    Assert.assertEquals(1, catalog.getClassRelatedToTag().size());
    
    Assert.assertEquals(c,catalog.getClassRelatedToTag().get(0));
    Assert.assertEquals(c2,catalog.getClassNotRelatedToTag().get(0));
    
    catalog.remove("被关联的类");
    catalog.remove("无关联的类");
    Assert.assertEquals(0, catalog.size());
  }
}
