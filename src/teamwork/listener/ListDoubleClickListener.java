package teamwork.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;
import javax.swing.JTextArea;

import teamwork.r.R;
import teamwork.window.NewsTextWindow;

public class ListDoubleClickListener implements MouseListener {

  @Override
  public void mouseClicked(MouseEvent arg0) {
    if (arg0.getClickCount() == 2) {
      JList<String> list = (JList<String>) arg0.getSource();
      String title = (String) list.getSelectedValue();
      new NewsTextWindow();
      R r = R.getInstance();
      JTextArea title_tf = null;
      JTextArea content = null;
      title_tf = (JTextArea) r.getObject("newsTextWindow_textArea_title");
      content = (JTextArea) r.getObject("newsTextWindow_textArea_content");
      content
          .setText("��������10��6�յ磨���߸߽���������ʡ�����조���������ȫ��������ߵ��߽츣�ݶ����»�����ڸ�������Ժ������������ʽ�ϣ��й��쵼Ϊ�����»־Ը��������죬������10�ҵ��½������͡����ݵ��µ��͡����鼮����ũ�����ݡ�����ͼ���ҡ���ͼ��ݡ���Сѧͼ��ݡ����ض�ͯ�������ͼ�飬������������У԰���ֻ��Ķ�ƽ̨�����조���������ȫ��������ԡ����︣�������ƶ���ѧ��չ��Ϊ���⡣��ڼ䣬���ؽ���չ������Ŀ�Ƽ����������˼ҡ���ͼ��Ԯ���Ȼ���Լ���ϲӭʮ�˴󡱰�������ͼ��չ����Ʒ�ж����ȡ�");
      title_tf.setText(title);
    }
  }

  @Override
  public void mouseEntered(MouseEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseExited(MouseEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mousePressed(MouseEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseReleased(MouseEvent arg0) {
    // TODO Auto-generated method stub

  }

}
