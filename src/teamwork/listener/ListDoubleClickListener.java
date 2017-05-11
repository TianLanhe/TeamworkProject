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
      // ȡ�ñ����������
      JList<News> list = (JList<News>) arg0.getSource();
      News news = list.getSelectedValue();

      if (news != null) {
        // ȡ���б����������ţ��������·�ҳ
        List<News> newsList = ((NewsListModel<News>) list.getModel()).getListData();
        // ����ǰѡ������źͱ�ǩ���ݸ�������ϸ���沢��ʾ
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
