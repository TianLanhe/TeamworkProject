package teamwork.transaction;

import teamwork.model.News;
import teamwork.model.NewsCatalog;

public class LoadAddNewsTransaction extends LoadTransaction {

  public LoadAddNewsTransaction(String str) {
    super(str);
  }

  @Override
  protected void parseCommand(String command) {
    String[] words = command.split(" ");

    String[] keyVal;
    String field;
    String text;
    News news = new News();
    for (int i = 1; i < words.length; ++i) {
      keyVal = words[i].split(":");
      field = keyVal[0];
      text = keyVal.length > 1 ? keyVal[1] : "";
      if ("url".equals(field)) {
        news.setUrl(text);
      } else if ("content".equals(field)) {
        news.setContent(text);
      } else if ("type".equals(field)) {
        news.setType(text);
      } else if ("location".equals(field)) {
        news.setLocation(text);
      } else if ("id".equals(field)) {
        news.setId(text);
      } else if ("date".equals(field)) {
        news.setDate(text);
      } else if ("title".equals(field)) {
        news.setTitle(text);
      }
    }
    NewsCatalog.getInstance().add(news);
  }

}
