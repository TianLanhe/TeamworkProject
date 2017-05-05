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
      // 取得被点击的新闻
      JList<News> list = (JList<News>) arg0.getSource();
      News news = list.getSelectedValue();

      if (news != null) {
        // 取得最后一个标签名，来确定当前被选择的是哪个标签
        // 不能通过JTree确定当前选择了哪个标签
        String[] tagNames = label.getText().split("\\s");
        String lastTagName = tagNames[tagNames.length - 1];

        // 在ClassCatalog中查找并取得该名字的标签
        Tag tag = null;
        ClassCatalog catalog = ClassCatalog.getInstance();
        for (NewsClass c : catalog.getClassList()) {
          int index = c.indexOfTag(new Tag(lastTagName));
          if (index != -1) {
            tag = c.getTag(index);
            break;
          }
        }
        // 将当前选择的新闻和标签传递给新闻详细界面并显示
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
