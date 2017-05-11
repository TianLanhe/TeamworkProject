package teamwork.test;

import org.junit.Assert;
import org.junit.Test;

import teamwork.model.ClassAndTagChoiceManager;
import teamwork.model.NewsClass;
import teamwork.model.Tag;

public class ChoiceManagerTest {


  @Test
  public void managerInitTest() {
    ClassAndTagChoiceManager manager = new ClassAndTagChoiceManager();

    Assert.assertEquals(0, manager.tagSize());
    Assert.assertEquals(0, manager.classSize());
  }

  @Test
  public void managerAddTagTest() {
    ClassAndTagChoiceManager manager = new ClassAndTagChoiceManager();

    NewsClass c_single = new NewsClass("类别");
    Tag tag = new Tag("标签1", c_single);
    c_single.addTag(tag);

    Assert.assertFalse(manager.hasClass(c_single));
    Assert.assertFalse(manager.hasTag(tag));
    
    manager.addWithoutCheck(tag);
    Assert.assertTrue(manager.hasClass(c_single));
    Assert.assertTrue(manager.hasTag(tag));
    Assert.assertEquals(1, manager.tagSize());
    Assert.assertEquals(1, manager.classSize());

    tag = new Tag("标签2", c_single);
    c_single.addTag(tag);
    manager.addWithoutCheck(tag);
    Assert.assertEquals(2, manager.tagSize());
    Assert.assertEquals(1, manager.classSize());
  }

  @Test
  public void managerAlterTagTest() {
    ClassAndTagChoiceManager manager = new ClassAndTagChoiceManager();

    NewsClass c_single = new NewsClass("类别");
    Tag tag = new Tag("标签1", c_single);
    c_single.addTag(tag);
    
    Assert.assertFalse(manager.hasClass(c_single));
    Assert.assertFalse(manager.hasTag(tag));

    manager.addWithoutCheck(tag);
    Assert.assertTrue(manager.hasClass(c_single));
    Assert.assertTrue(manager.hasTag(tag));
    Assert.assertEquals(1, manager.tagSize());
    Assert.assertEquals(1, manager.classSize());

    tag = new Tag("标签2", c_single);
    c_single.addTag(tag);
    manager.alterChoiceToTag(tag);
    Assert.assertEquals(1, manager.tagSize());
    Assert.assertEquals(1, manager.classSize());
  }
  
  @Test
  public void managerRemoveTagTest() {
    ClassAndTagChoiceManager manager = new ClassAndTagChoiceManager();
    
    NewsClass c = new NewsClass("类别");
    Tag tag1 = new Tag("标签1", c);
    Tag tag2 = new Tag("标签2", c);
    c.addTag(tag1);
    c.addTag(tag2);
    
    manager.addWithoutCheck(tag1);
    manager.addWithoutCheck(tag2);
    Assert.assertTrue(manager.hasClass(c));
    Assert.assertTrue(manager.hasTag(tag1));
    Assert.assertTrue(manager.hasTag(tag2));
    
    manager.removeTag(tag1);
    Assert.assertTrue(manager.hasClass(c));
    Assert.assertFalse(manager.hasTag(tag1));
    Assert.assertTrue(manager.hasTag(tag2));
    
    manager.removeTag(tag2);
    Assert.assertFalse(manager.hasClass(c));
    Assert.assertFalse(manager.hasTag(tag1));
    Assert.assertFalse(manager.hasTag(tag2));
  }
}
