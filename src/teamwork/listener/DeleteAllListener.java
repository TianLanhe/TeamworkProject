package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JOptionPane;

import teamwork.model.BinCatalog;
import teamwork.model.News;
import teamwork.model.NewsCatalog;
import teamwork.model.viewmodel.NewsListModel;
import teamwork.r.R;

public class DeleteAllListener implements ActionListener {
  private JList<News> newsList;

  @SuppressWarnings("unchecked")
  public DeleteAllListener() {
    newsList = (JList<News>) R.getInstance().getObject("newsList");
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    if (newsList.getModel().getSize() != 0) {
      int confirmPane =
          JOptionPane.showConfirmDialog(null, "ȷ������ɾ������������", "����ɾ������", JOptionPane.YES_NO_OPTION);
      if (confirmPane == JOptionPane.YES_OPTION) {
        for (News news : BinCatalog.getInstance().getNewsList())
          NewsCatalog.getInstance().remove(news);

        BinCatalog.getInstance().getNewsList().clear();
        ((NewsListModel<News>) newsList.getModel()).notifyDataChanged();
      }
    }
  }
}
