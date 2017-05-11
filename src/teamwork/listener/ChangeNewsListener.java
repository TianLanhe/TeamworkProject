package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import teamwork.model.News;
import teamwork.r.R;
import teamwork.window.NewsTextWindow;

public class ChangeNewsListener implements ActionListener {

  List<News> newsList;
  NewsTextWindow window;

  public ChangeNewsListener(List<News> newsList) {
    this.newsList = newsList;
    window = (NewsTextWindow) R.getInstance().getObject("NewsTextWindow");
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    String command = arg0.getActionCommand();
    News news = window.getNews();

    int index = newsList.indexOf(news);
    if (command.equals("prev")) {
      index = Math.max(0, index - 1);
    } else if (command.equals("next")) {
      index = Math.min(newsList.size() - 1, index + 1);
    }
    news = newsList.get(index);
    window.changeNews(news);
  }
}
