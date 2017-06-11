package teamwork.diagram;

public class DiagramFactory {

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
