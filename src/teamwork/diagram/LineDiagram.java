package teamwork.diagram;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;

public class LineDiagram extends Diagram {

  public LineDiagram(double[] values, String[] keys) {
    super(values, keys);
  }

  @Override
  protected JFreeChart createDiagram() {
    return ChartFactory.createLineChart(super.title, super.categoryAxisLabel, super.valueAxisLabel,
        super.categoryDataset);
  }
}
