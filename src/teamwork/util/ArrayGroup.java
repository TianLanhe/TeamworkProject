package teamwork.util;

import java.util.Arrays;

public class ArrayGroup {

  private int start;
  private int distance;
  private int[] keys;
  private double[] values;

  public ArrayGroup(String[] k, double[] values, int start, int distance) {
    setArrayGroup(k, values, start, distance);
  }

  public ArrayGroup() {
    keys = new int[0];
    values = new double[0];
    distance = 0;
    start = 0;
  }

  // 传入要转换的键值数据
  public void setArrayGroup(String[] k, double[] values, int start, int distance) {
    this.start = start;
    this.distance = distance;

    keys = new int[k.length];
    for (int i = 0; i < keys.length; ++i) {
      keys[i] = Integer.parseInt(k[i]);
    }

    // 只截取start之后的数据
    int pos = 0;
    while (pos < keys.length && start > keys[pos])
      ++pos;

    keys = Arrays.copyOfRange(keys, pos, keys.length);
    this.values = Arrays.copyOfRange(values, pos, values.length);
  }

  // 获得转换后的String数组
  public String[] getCategoryGroup() {
    String[] ret = new String[keys.length];

    if (distance <= 1) {
      for (int i = 0; i < keys.length; ++i)
        ret[i] = Integer.toString(keys[i]);
    } else {
      int i = 0;
      int j = start;
      while (j <= keys[keys.length - 1]) {
        ret[i++] = j + "-" + (j + distance - 1);
        j += distance;
      }
      ret = Arrays.copyOfRange(ret, 0, i);
    }
    return ret;
  }

  // 获得转换后的double数组
  public double[] getValueGroup() {
    if (distance <= 1) {
      return values;
    } else {
      double[] ret = new double[values.length];
      int n = 0;
      int j = start + distance - 1;
      for (int i = 0; i < values.length; ++i) {
        while (j < keys[i]) {
          j += distance;
          ++n;
        }
        ret[n] += values[i];
      }
      return Arrays.copyOfRange(ret, 0, n + 1);
    }
  }

}
