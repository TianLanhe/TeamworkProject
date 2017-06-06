package teamwork.diagram;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;

public class BarDiagram extends Diagram {

  public BarDiagram(double[] values, String[] keys) {
    super(values, keys);
  }

  @Override
  protected JFreeChart createDiagram() {
    return ChartFactory.createBarChart(super.title, super.categoryAxisLabel, super.valueAxisLabel,
        super.categoryDataset);
  }

}
