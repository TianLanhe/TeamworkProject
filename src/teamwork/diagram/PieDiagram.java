package teamwork.diagram;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryToPieDataset;
import org.jfree.util.TableOrder;

public class PieDiagram extends Diagram {
  private CategoryToPieDataset dataset;

  public PieDiagram(double[] values, String[] keys) {
    super(values, keys);
  }

  @Override
  protected JFreeChart createDiagram() {
    dataset = new CategoryToPieDataset(super.categoryDataset, TableOrder.BY_ROW, 0);
    return ChartFactory.createPieChart(super.getTitle(), dataset,true,true,false);
  }
}
