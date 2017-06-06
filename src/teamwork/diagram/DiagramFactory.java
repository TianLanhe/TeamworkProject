package teamwork.diagram;

import teamwork.util.ArrayGroup;

public class DiagramFactory {
  private static ArrayGroup arrayGroup;

  public static Diagram createDiagram(String chartName, double[] values, String[] keys, int start,
      int memberNum) {
    arrayGroup = new ArrayGroup(keys, values, start, memberNum);

    double[] groupValues = arrayGroup.getValueGroup();
    String[] groupKeys = arrayGroup.getCategoryGroup();

    return createDiagram(chartName, groupValues, groupKeys);
  }

  public static Diagram createDiagram(String chartName, double[] groupValues, String[] groupKeys) {
    if (chartName.equals("bar")) {
      return new BarDiagram(groupValues, groupKeys);
    } else if (chartName.equals("line")) {
      return new LineDiagram(groupValues, groupKeys);
    } else if (chartName.equals("pie")) {
      return new PieDiagram(groupValues, groupKeys);
    } else if (chartName.equals("spider")) {
      return new SpiderDiagram(groupValues, groupKeys);
    } else {
      return null;
    }
  }
}
