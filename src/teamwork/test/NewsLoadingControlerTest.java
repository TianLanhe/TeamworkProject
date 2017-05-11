package teamwork.test;


import org.junit.Assert;
import org.junit.Test;

import teamwork.controler.NewsLoadingControler;
import teamwork.loader.NewsLoader;

public class NewsLoadingControlerTest {

  @Test
  public void loadFileFailedTest(){
    NewsLoadingControler controler = new NewsLoadingControler(new NewsLoader());
    int count = controler.loadData("do not existed");
    Assert.assertEquals(-1, count);
  }
}
