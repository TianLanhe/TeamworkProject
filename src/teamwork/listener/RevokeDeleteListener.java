package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JList;
import javax.swing.JOptionPane;

import teamwork.model.News;
import teamwork.model.NewsListModel;
import teamwork.r.R;

public class RevokeDeleteListener implements ActionListener {

  private JList<News> newsList;

  @SuppressWarnings("unchecked")
  public RevokeDeleteListener() {
    newsList = (JList<News>) R.getInstance().getObject("newsList");
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    List<News> newsSelected = newsList.getSelectedValuesList();
    if (newsSelected.size() == 0) {
      JOptionPane.showMessageDialog(null, "请用鼠标和Ctrl/Alt选择一条或多条新闻 ！", "恢复新闻",
          JOptionPane.INFORMATION_MESSAGE);
    } else {
      for (News news : newsSelected)
        news.revoke();
      ((NewsListModel<News>) newsList.getModel()).notifyDataChanged();
    }
  }

}
