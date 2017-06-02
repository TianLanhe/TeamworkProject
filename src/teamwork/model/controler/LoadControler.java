package teamwork.model.controler;

import java.io.File;

import teamwork.transaction.TransactionSource;
import teamwork.transaction.factory.LoadTransactionFactory;
import teamwork.util.XMLParser;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class LoadControler {

  XMLParser parser = new XMLParser();// XML解析器

  public boolean load(String fileName) {
    return load(new File(fileName));
  }

  public boolean load(File file) {
    // 若文件不存在，则返回false
    if (!file.exists()) return false;

    if (!parser.loadFrom(file, "Transaction")) {
      return false;
    }

    TransactionSource source = new TransactionSource();
    LoadTransactionFactory factory = new LoadTransactionFactory();

    while (parser.hasNext()) {
      source.add(factory.create(parser.next()));
    }

    source.run();
    return true;
  }

  public String loadPassword(String fileName) {
    return loadPassword(new File(fileName));
  }

  public String loadPassword(File file) {
    // 若文件不存在，则返回null
    if (!file.exists()) return "";

    // 解析失败，无Password标签，返回null
    if (!parser.loadFrom(file, "Password")) {
      return "";
    }

    if (parser.hasNext()) {
      Node node = parser.next();
      NamedNodeMap attrs = node.getAttributes();
      String pwd = attrs.getNamedItem("password").getNodeValue();
      return pwd;
    } else {
      return "";
    }
  }
}
