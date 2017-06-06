package teamwork.listener;


import java.util.Comparator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

import teamwork.diagram.Diagram;
import teamwork.diagram.DiagramFactory;
import teamwork.model.NewsClass;
import teamwork.model.Tag;
import teamwork.model.News;
import teamwork.r.R;

public class NewsTreeStatisticsListener extends MyTreeSelectionListener {

  JPanel diagramPanel;

  public NewsTreeStatisticsListener() {
    R r = R.getInstance();
    diagramPanel=(JPanel) r.getObject("diagramPanel");
    tagLabel = (JLabel) r.getObject("other_tagLabel");
    numLabel = (JLabel) r.getObject("other_numLabel");
  }

  @Override
  protected void showDetail() {
    DefaultMutableTreeNode node =
        (DefaultMutableTreeNode) tagsTree.getSelectionPath().getLastPathComponent();
    if (node.getUserObject() instanceof Tag) {
      getNewsList();

      displayLineDiagram();
      displayNumLabel();
      displayTagLabel();
    } else if (node.getUserObject() instanceof NewsClass) {

    }
  }

  private void displayLineDiagram() {
    list.sort(new NewsComparator());

    int index = -1;
    int year = 0;
    double[] num = new double[list.size()];
    String[] age = new String[list.size()];
    for (News news : list) {
      if (year != news.getYear()) {
        year = news.getYear();
        ++index;
        age[index] = String.valueOf(year);
      }
      ++num[index];
    }

    double[] values = new double[index + 1];
    String[] keys = new String[index + 1];

    System.arraycopy(age, 0, keys, 0, index + 1);
    System.arraycopy(num, 0, values, 0, index + 1);

    Diagram diagram = DiagramFactory.createDiagram("line", values, keys);
    diagramPanel.removeAll();
    diagramPanel.add(diagram.getPanel());
  }

  // 分类器，按年份分类
  private class NewsComparator implements Comparator<News> {
    @Override
    public int compare(News a, News b) {
      return a.getYear() - b.getYear();
    }
  }

}
