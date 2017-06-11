package teamwork.model.controler;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import teamwork.transaction.Transaction;
import teamwork.util.XMLParser;

public class DoTest {

  private String command;
  private String tagName;
  private String className;
  private String id;

  public double test(File file, File file2) {
    XMLParser parser = new XMLParser();

    // 每个id映射到一张Map，该Map的键值分别是类别名，多个标签名
    Map<String, Map<String, List<String>>> map = new HashMap<String, Map<String, List<String>>>();
    Map<String, Map<String, List<String>>> map2 = new HashMap<String, Map<String, List<String>>>();

    // 判断两个文件是不是测试文件
    if (!parser.loadFrom(file, "TestTransactions") || !parser.hasNext()
        || !parser.loadFrom(file2, "TestTransactions") || !parser.hasNext()) {
      return -1;
    }
    // 解析文件1
    if (!parser.loadFrom(file, "Transaction")) {
      return -1;
    }
    // 将文件1贴标签的记录储存到map中
    while (parser.hasNext()) {
      parse(parser.next(), map);
    }
    // 解析文件2
    if (!parser.loadFrom(file2, "Transaction")) {
      return -1;
    }
    // 将文件2贴标签的记录储存到map2中
    while (parser.hasNext()) {
      parse(parser.next(), map2);
    }

    return compare(map, map2);
  }

  private double compare(Map<String, Map<String, List<String>>> map,
      Map<String, Map<String, List<String>>> map2) {
    if (map.size() != map2.size()) {
      return -1;
    }

    double count = 0;
    boolean flag;
    for (String id : map.keySet()) {
      if (!map2.containsKey(id)) {
        return -1;
      }
      if (map.get(id).size() != map2.get(id).size()) {
        continue;
      }
      flag = true;
      for (String className : map.get(id).keySet()) {
        if (!map2.get(id).containsKey(className)
            || map.get(id).get(className).size() != map2.get(id).get(className).size()
            || !map.get(id).get(className).containsAll(map2.get(id).get(className))) {
          flag = false;
          break;
        }
      }
      if (flag) {
        ++count;
      }
    }
    return count / map.size();
  }

  private void parse(Node node, Map<String, Map<String, List<String>>> map) {
    command = node.getAttributes().getNamedItem("name").getNodeValue();
    if (command.equals(Transaction.POST_TAG)) {
      id = node.getAttributes().getNamedItem("id").getNodeValue();

      NodeList childNode = node.getChildNodes();
      for (int i = 0; i < childNode.getLength(); ++i) {
        Node n = childNode.item(i);
        if (n.getNodeName().equals("Tag")) {
          className = n.getAttributes().getNamedItem("parent").getNodeValue();
          tagName = n.getAttributes().getNamedItem("name").getNodeValue();

          break;
        }
      }

      if (!map.containsKey(id)) {
        map.put(id, new HashMap<String, List<String>>());
      }
      if (!map.get(id).containsKey(className)) {
        map.get(id).put(className, new ArrayList<String>());
      }
      map.get(id).get(className).add(tagName);
    }
  }
}
