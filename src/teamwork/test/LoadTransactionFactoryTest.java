package teamwork.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import teamwork.transaction.LoadAddNewsClassTransaction;
import teamwork.transaction.LoadAddNewsTransaction;
import teamwork.transaction.LoadAddTagTransaction;
import teamwork.transaction.LoadDeleteNewsTransaction;
import teamwork.transaction.LoadPostTagTransaction;
import teamwork.transaction.LoadTagRelationTransaction;
import teamwork.transaction.Transaction;
import teamwork.transaction.factory.LoadTransactionFactory;

public class LoadTransactionFactoryTest {

  private static LoadTransactionFactory factory;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    factory = new LoadTransactionFactory();
  }

  @Test
  public void createLoadAddTagTransactionTest() {
    Transaction transaction = factory.create("addTag ");
    Assert.assertTrue(transaction instanceof LoadAddTagTransaction);
  }

  @Test
  public void createLoadAddNewsClassTransactionTest() {
    Transaction transaction = factory.create("addNewsClass ");
    Assert.assertTrue(transaction instanceof LoadAddNewsClassTransaction);
  }

  @Test
  public void createLoadDeleteNewsTransactionTest() {
    Transaction transaction = factory.create("deleteNews ");
    Assert.assertTrue(transaction instanceof LoadDeleteNewsTransaction);
  }

  @Test
  public void createLoadAddNewsTransactionTest() {
    Transaction transaction = factory.create("addNews ");
    Assert.assertTrue(transaction instanceof LoadAddNewsTransaction);
  }

  @Test
  public void createLoadPostTagTransactionTest() {
    Transaction transaction = factory.create("postTag ");
    Assert.assertTrue(transaction instanceof LoadPostTagTransaction);
  }

  @Test
  public void createLoadTagRelationTransactionTest() {
    Transaction transaction = factory.create("addRelation ");
    Assert.assertTrue(transaction instanceof LoadTagRelationTransaction);
  }
}
