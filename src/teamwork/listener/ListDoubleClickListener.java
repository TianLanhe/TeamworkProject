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
          .setText("本报福州10月6日电（记者高建进）福建省第六届“书香八闽”全民读书月暨第七届福州读书月活动今天在福建博物院启动。启动仪式上，有关领导为读书月活动志愿服务队授旗，向福州市10家道德讲堂赠送《福州道德典型》等书籍，向农家书屋、乡镇图书室、县图书馆、中小学图书馆、留守儿童代表捐赠图书，并开启“书香校园”手机阅读平台。本届“书香八闽”全民读书月以“弘扬福建精神，推动科学发展”为主题。活动期间，各地将开展优秀书目推荐、“书香人家”和图书援助等活动，以及“喜迎十八大”百种优秀图书展及精品诵读晚会等。");
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
