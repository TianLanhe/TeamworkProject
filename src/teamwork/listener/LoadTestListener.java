package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import teamwork.model.ClassCatalog;
import teamwork.model.News;
import teamwork.model.NewsCatalog;
import teamwork.model.controler.LoadTestControler;
import teamwork.model.viewmodel.NewsListModel;
import teamwork.r.R;

public class LoadTestListener implements ActionListener {

  private JList<News> newsList;

  @SuppressWarnings("unchecked")
  public LoadTestListener() {
    newsList = (JList<News>) R.getInstance().getObject("testNewsList");
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    JFileChooser jfc = new JFileChooser();
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);// ֻ��ѡ���ļ�
    FileNameExtensionFilter filter = new FileNameExtensionFilter("�����ļ�(*.test)", "test");
    jfc.setFileFilter(filter);

    int state = jfc.showDialog(null, "��ȡ");
    if (state == JFileChooser.APPROVE_OPTION) {
      File file = jfc.getSelectedFile();

      // �������ѡ���ļ��Ƿ����
      if (!file.exists()) {
        JOptionPane.showMessageDialog(null, "�ļ������ڣ���ȡʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
      } else {
        // ���ClassCatalog��NewsCatalog������
        ClassCatalog.getInstance("testCatalog").clear();
        NewsCatalog.getInstance("testCatalog").clear();

        if (new LoadTestControler().load(file)) {
          ((NewsListModel<News>) newsList.getModel()).setListData(NewsCatalog.getInstance(
              "testCatalog").getNewsList());
        } else {
          JOptionPane.showMessageDialog(null, "��ȡʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
        }
      }
    }
  }

}
