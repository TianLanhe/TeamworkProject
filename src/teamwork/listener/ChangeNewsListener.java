package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import teamwork.model.News;
import teamwork.model.NewsCatalog;
import teamwork.r.R;
import teamwork.window.NewsTextWindow;

public class ChangeNewsListener implements ActionListener {

  News news;
  NewsTextWindow window;

  public ChangeNewsListener(News news) {
    this.news = news;
    window = (NewsTextWindow) R.getInstance().getObject("NewsTextWindow");
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    NewsCatalog catalog = NewsCatalog.getInstance();
    String command = arg0.getActionCommand();
    if (command.equals("prev")) {
      news = catalog.findPrev(news);
    } else if (command.equals("next")) {
      news = catalog.findNext(news);
    }
    window.changeNews(news);
  }
}
