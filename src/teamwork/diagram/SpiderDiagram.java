package teamwork.diagram;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.SpiderWebPlot;

public class SpiderDiagram extends Diagram {

  public SpiderDiagram(double[] values, String[] keys) {
    super(values, keys);
  }

  @Override
  protected JFreeChart createDiagram() {
    SpiderWebPlot spiderwebplot = new SpiderWebPlot(super.categoryDataset);
    return new JFreeChart(super.title, spiderwebplot);
  }
}
