package teamwork.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import teamwork.updater.NewsUpdater;

public class News {
  private String id;// 唯一标识符
  private String title;// 标题
  private String url;// 网页链接
  private String content;// 新闻内容
  private Calendar date;// 日期
  private String location;// 报社
  private String type;// 新闻类型
  private List<Tag> tagsList;// 标签列表

  public News(String id, String title, String content, String date, String location, String type,
      String url) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.location = location;
    this.type = type;
    this.url = url;
    this.setDate(date);
    tagsList = new ArrayList<Tag>();
  }

  public News() {
    this("", "", "", "", "", "", "");
  }

  public boolean update() {
    if (content.isEmpty()) {
      NewsUpdater newsUpdater = new NewsUpdater();
      if (!newsUpdater.connect(url)) {
        return false;
      }
      content = newsUpdater.getContent();
    }
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

  public void delete() {
    // TODO
  }

  public boolean postTag(Tag tag) {
    if (hasTag(tag)) {
      return false;
    }
    tagsList.add(tag);
    tag.addNews(this);
    return true;
  }

  public boolean hasTag(Tag tag) {
    return tagsList.contains(tag);
  }

  public boolean hasClass(NewsClass c) {
    for (Tag tag : tagsList) {
      if (c.containsTag(tag)) return true;
    }
    return false;
  }

  public void removeTag(int i) {
    tagsList.remove(i);
  }

  public void removeTag(Tag tag) {
    tagsList.remove(tag);
  }

  public Tag getTag(int i) {
    return tagsList.get(i);
  }

  public Tag getTag(String tagName) {
    for (Tag tag : tagsList) {
      if (tag.getName().equals(tagName)) {
        return tag;
      }
    }
    return null;
  }

  public int indexOfTag(Tag tag) {
    return tagsList.indexOf(tag);
  }

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

  public List<Tag> getTagsList() {
    return tagsList;
  }

  public void setTagsList(List<Tag> tagsList) {
    this.tagsList = tagsList;
  }
}
