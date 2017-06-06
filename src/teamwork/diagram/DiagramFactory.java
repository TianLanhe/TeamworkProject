package teamwork.diagram;

import teamwork.util.ArrayGroup;

public class DiagramFactory {
  private static ArrayGroup arrayGroup;

  public static Diagram createDiagram(String chartName, double[] values, String[] keys, int start,
      int memberNum) {
    arrayGroup = new ArrayGroup(keys, values, start, memberNum);

    double[] groupValues = arrayGroup.getValueGroup();
    String[] groupKeys = arrayGroup.getCategoryGroup();

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

  public static Diagram createDiagram(String chartName, double[] values, String[] keys) {
    int start;
    if (keys.length != 0)
      start = Integer.parseInt(keys[0]);
    else
      start = 0;
    return createDiagram(chartName, values, keys, start, 0);
  }
}
