package teamwork.diagram;

import java.awt.Font;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

public abstract class Diagram {

  protected String frameTitle = "统计图";
  protected String title = "";
  protected String categoryAxisLabel = "";
  protected String valueAxisLabel = "";

  private JFreeChart diagram;
  protected DefaultCategoryDataset categoryDataset;

  public Diagram(double[] values, String[] keys) {
    setCategoryDataset(values, keys);

    StandardChartTheme mChartTheme = new StandardChartTheme("CN");
    mChartTheme.setExtraLargeFont(new Font("微软雅黑", Font.BOLD, 20)); // 设置标题字体
    mChartTheme.setLargeFont(new Font("微软雅黑", Font.CENTER_BASELINE, 12)); // 设置轴向字体
    mChartTheme.setRegularFont(new Font("微软雅黑", Font.PLAIN, 12)); // 设置图例字体
    ChartFactory.setChartTheme(mChartTheme);

    diagram = createDiagram();
    diagram.removeLegend();
  }

  // 不同统计图返回不同对象
  protected abstract JFreeChart createDiagram();

  // 设置图形键值内容
  public void setCategoryDataset(double[] values, String[] keys) {
    if (values.length != keys.length) {
      System.out.println("Error! Length of each paramter is not equal!");
      System.exit(0);
    }

    categoryDataset = new DefaultCategoryDataset();

    for (int i = 0; i < values.length; ++i) {
      categoryDataset.addValue(values[i], "", keys[i]);
    }
  }

  public void draw() {
    ChartFrame frame = new ChartFrame(frameTitle, diagram, true);
    frame.pack();
    RefineryUtilities.centerFrameOnScreen(frame);
    frame.setVisible(true);
  }

  public JPanel getPanel() {
    return new ChartPanel(diagram);
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public String getFrameTitle() {
    return frameTitle;
  }

  public void setFrameTitle(String frameTitle) {
    this.frameTitle = frameTitle;
  }

  public String getCategoryAxisLabel() {
    return categoryAxisLabel;
  }

  public void setCategoryAxisLabel(String categoryAxisLabel) {
    this.categoryAxisLabel = categoryAxisLabel;
  }

  public DefaultCategoryDataset getCategoryDataset() {
    return categoryDataset;
  }

  public String getValueAxisLabel() {
    return valueAxisLabel;
  }

  public void setValueAxisLabel(String valueAxisLabel) {
    this.valueAxisLabel = valueAxisLabel;
  }

}
