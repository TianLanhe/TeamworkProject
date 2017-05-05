package teamwork.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JList;

import teamwork.model.ClassCatalog;
import teamwork.model.News;
import teamwork.model.NewsClass;
import teamwork.model.Tag;
import teamwork.r.R;
import teamwork.window.NewsTextWindow;

public class ListDoubleClickListener implements MouseListener {

  private JLabel label;

  public ListDoubleClickListener() {
    R r = R.getInstance();
    label = (JLabel) r.getObject("tagLabel");
  }

  @SuppressWarnings("unchecked")
  @Override
  public void mouseClicked(MouseEvent arg0) {
    if (arg0.getClickCount() == 2) {
      // ȡ�ñ����������
      JList<News> list = (JList<News>) arg0.getSource();
      News news = list.getSelectedValue();

      if (news != null) {
        // ȡ�����һ����ǩ������ȷ����ǰ��ѡ������ĸ���ǩ
        // ����ͨ��JTreeȷ����ǰѡ�����ĸ���ǩ
        String[] tagNames = label.getText().split("\\s");
        String lastTagName = tagNames[tagNames.length - 1];

        // ��ClassCatalog�в��Ҳ�ȡ�ø����ֵı�ǩ
        Tag tag = null;
        ClassCatalog catalog = ClassCatalog.getInstance();
        for (NewsClass c : catalog.getClassList()) {
          int index = c.indexOfTag(new Tag(lastTagName));
          if (index != -1) {
            tag = c.getTag(index);
            break;
          }
        }
        // ����ǰѡ������źͱ�ǩ���ݸ�������ϸ���沢��ʾ
        new NewsTextWindow(news, tag).run();
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
