package teamwork.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JList;

import teamwork.model.News;
import teamwork.model.NewsListModel;
import teamwork.window.NewsTextWindow;

public class ListDoubleClickListener implements MouseListener {

  @SuppressWarnings("unchecked")
  @Override
  public void mouseClicked(MouseEvent arg0) {
    if (arg0.getClickCount() == 2) {
      // 取得被点击的新闻
      JList<News> list = (JList<News>) arg0.getSource();
      News news = list.getSelectedValue();

      if (news != null) {
        // 取得列表中所有新闻，用于上下翻页
        List<News> newsList = ((NewsListModel<News>) list.getModel()).getListData();
        // 将当前选择的新闻和标签传递给新闻详细界面并显示
        new NewsTextWindow(news, newsList).run();
      }
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
