package teamwork.util;

public class GetBriefLocation extends GetLocation {

  @Override
  public String parse(String location) {
    int index = location.indexOf("±¨");
    String ret = index == -1 ? location : location.substring(0, index + 1);
    return ret;
  }

}
