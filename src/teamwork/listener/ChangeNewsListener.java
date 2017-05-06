package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import teamwork.model.News;
import teamwork.model.Tag;
import teamwork.r.R;
import teamwork.window.NewsTextWindow;

public class ChangeNewsListener implements ActionListener {

  Tag tag;
  NewsTextWindow window;

  public ChangeNewsListener(Tag tag) {
    this.tag = tag;
    window = (NewsTextWindow) R.getInstance().getObject("NewsTextWindow");
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    String command = arg0.getActionCommand();
    News news = window.getNews();
    if (command.equals("prev")) {
      news = tag.findPrevNews(news);
    } else if (command.equals("next")) {
      news = tag.findNextNews(news);
    }
    window.changeNews(news);
  }
}
