package teamwork.model;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import teamwork.model.controler.NewsDeleteControler;
import teamwork.model.controler.PostTagMediator;
import teamwork.model.controler.TearTagMediator;
import teamwork.updater.NewsUpdater;

public class News{
  private String id;// 唯一标识符
  private String title;// 标题
  private String url;// 网页链接
  private String content;// 新闻内容
  private Calendar date;// 日期
  private String location;// 报社
  private String type;// 新闻类型

  private ClassAndTagChoiceManager manager;

  public News(String id, String title, String content, String date, String location, String type,
      String url) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.location = location;
    this.type = type;
    this.url = url;
    this.setDate(date);
    manager = new ClassAndTagChoiceManager();
  }

  public News() {
    this("", "", "", "", "", "", "");
  }

  public boolean update() {
    NewsUpdater newsUpdater = new NewsUpdater();
    if (!newsUpdater.connect(url)) {
      return false;
    }
    content = newsUpdater.getContent();
    return true;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj != null && obj.getClass().equals(this.getClass())) {
      News news = (News) obj;
      return news.id.equals(id);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = result * 31 + id.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return getTitle();
  }

  public boolean postTag(Tag tag) {
    return new PostTagMediator(this, tag).post();
  }

  public boolean tearTag(Tag tag) {
    return new TearTagMediator(this, tag).tear();
  }

  public boolean delete() {
    return new NewsDeleteControler(this).delete();
  }

  public void revoke() {
    new NewsDeleteControler(this).revoke();
  }

  public boolean hasTag(Tag tag) {
    return manager.hasTag(tag);
  }

  public boolean hasClass(NewsClass c) {
    return manager.hasClass(c);
  }

  public void removeTag(Tag tag) {
    manager.removeTag(tag);
  }

  public Tag[] getTags() {
    return manager.getTags();
  }

  public List<Tag> getTagsList() {
    Tag[] tags = getTags();
    return Arrays.asList(tags);
  }

  public int sizeTag() {
    return manager.tagSize();
  }
  
  public int getYear(){
    return date.get(Calendar.YEAR);
  }

  // //////////////////////////////////////////////
  // //////////////////////////////////////////////
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getDate() {
    int year = date.get(Calendar.YEAR);
    int month = date.get(Calendar.MONTH) + 1;
    int day = date.get(Calendar.DATE);
    String ret = String.format("%04d-%02d-%02d", year, month, day);
    return ret;
  }

  public void setDate(String date) {
    String[] arr = date.split("-");
    this.date = Calendar.getInstance();
    if (arr.length != 3)
      this.date.set(0, 0, 0);
    else
      this.date.set(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]) - 1,
          Integer.parseInt(arr[2]));
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public ClassAndTagChoiceManager getManager() {
    return manager;
  }

  public void setManager(ClassAndTagChoiceManager manager) {
    this.manager = manager;
  }
}
