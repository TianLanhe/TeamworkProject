package teamwork.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;

import teamwork.model.News;
import teamwork.window.NewsTextWindow;

public class ListDoubleClickListener implements MouseListener {

  @SuppressWarnings("unchecked")
  @Override
  public void mouseClicked(MouseEvent arg0) {
    if (arg0.getClickCount() == 2) {
      JList<News> list = (JList<News>) arg0.getSource();
      News news = list.getSelectedValue();
      new NewsTextWindow(news).run();
    }
  }

  @Override
  public void mouseEntered(MouseEvent arg0) {}

  @Override
  public void mouseExited(MouseEvent arg0) {}

  @Override
  public void mousePressed(MouseEvent arg0) {}

  @Override
  public void mouseReleased(MouseEvent arg0) {}

}
