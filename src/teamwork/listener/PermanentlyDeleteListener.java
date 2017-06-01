package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JList;
import javax.swing.JOptionPane;

import teamwork.model.BinCatalog;
import teamwork.model.News;
import teamwork.model.NewsCatalog;
import teamwork.model.viewmodel.NewsListModel;
import teamwork.r.R;

public class PermanentlyDeleteListener implements ActionListener {

  private JList<News> newsList;

  @SuppressWarnings("unchecked")
  public PermanentlyDeleteListener() {
    newsList = (JList<News>) R.getInstance().getObject("newsList");
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    List<News> newsSelected = newsList.getSelectedValuesList();
    if (newsSelected.size() == 0) {
      JOptionPane.showMessageDialog(null, "请用鼠标和Ctrl/Alt选择一条或多条新闻 ！", "彻底删除新闻",
          JOptionPane.INFORMATION_MESSAGE);
    } else {
      int confirmPane =
          JOptionPane.showConfirmDialog(null, "确定彻底删除您选中的 " + newsSelected.size() + " 条新闻吗？",
              "彻底删除新闻", JOptionPane.YES_NO_OPTION);
      if (confirmPane == JOptionPane.YES_OPTION) {
        for (News news : newsSelected) {
          NewsCatalog.getInstance().remove(news);
          BinCatalog.getInstance().remove(news);
        }
        ((NewsListModel<News>) newsList.getModel()).notifyDataChanged();
      }
    }
  }

}
