package teamwork.test;

import org.junit.Assert;
import org.junit.Test;

import teamwork.model.NewsClass;
import teamwork.model.Tag;

public class NewsClassTest {

  @Test
  public void classEqualTest() {
    NewsClass c1 = new NewsClass("class name");
    NewsClass c2 = new NewsClass("class name");
    NewsClass c3 = new NewsClass("another class name");

    Assert.assertEquals(c1, c1);
    Assert.assertEquals(c1, c2);
    Assert.assertNotEquals(c1, c3);
  }

  @Test
  public void tagOperationTest() {
    NewsClass c = new NewsClass("class name");
    Assert.assertEquals(0, c.sizeTag());// sizeTag()

    c.addTag(new Tag("this is a tag"));// addTag()
    Assert.assertEquals(1, c.sizeTag());

    Tag tag = new Tag("another tag");
    Assert.assertFalse(c.containsTag(tag));// containsTag()

    c.addTag(tag);
    Assert.assertEquals(2, c.sizeTag());
    Assert.assertTrue(c.containsTag("another tag"));

    //不能重复添加相同标签到同一类别下
    c.addTag(tag);
    Assert.assertEquals(2, c.sizeTag());// addTag()

    Assert.assertEquals(0, c.indexOfTag("this is a tag"));// indexOfTag()
    Assert.assertEquals(1, c.indexOfTag(tag));

    c.removeTag(0);// removeTag
    Assert.assertEquals(1, c.sizeTag());

    c.removeTag("another tag");
    Assert.assertEquals(0, c.sizeTag());
  }

  @Test
  public void classAncestorTest() {
    NewsClass c = new NewsClass("class name");// c
    Assert.assertTrue(c.isAncestorOf(c));

    NewsClass c2 = new NewsClass("another class name");// c c2
    Assert.assertFalse(c.isAncestorOf(c2));

    Tag tag = new Tag("tag name");
    tag.setNextClass(c2);// tag->c2
    c.addTag(tag);// c->tag->c2
    Assert.assertTrue(c.isAncestorOf(c2));

    NewsClass c3 = new NewsClass("the third class name");// c->tag->c2 c3
    Assert.assertFalse(c.isAncestorOf(c3));

    Tag tag2 = new Tag("another tag name");
    tag2.setNextClass(c3);// c->tag->c2 tag2->c3
    c2.addTag(tag2);// c->tag->c2->tag2->c3
    Assert.assertTrue(c.isAncestorOf(c3));

    Assert.assertTrue(c2.isAncestorOf(c3));
    Assert.assertFalse(c3.isAncestorOf(c2));
    Assert.assertFalse(c3.isAncestorOf(c));
    Assert.assertFalse(c2.isAncestorOf(c));

    c.removeTag(0);// c tag->c2->tag2->c3
    Assert.assertFalse(c.isAncestorOf(c2));
    Assert.assertFalse(c.isAncestorOf(c3));
  }
  
  @Test
  public void classRelationWithTagTest(){
    NewsClass c = new NewsClass("class name");
    Assert.assertFalse(c.isRelatedToTag());
    
    c.addRelationToTag();
    Assert.assertTrue(c.isRelatedToTag());
    
    c.addRelationToTag();
    c.removeRelationToTag();
    Assert.assertTrue(c.isRelatedToTag());
    
    c.removeRelationToTag();
    Assert.assertFalse(c.isRelatedToTag());
  }
}
