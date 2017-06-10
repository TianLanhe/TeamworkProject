package teamwork.model.controler;

import java.io.File;

import teamwork.transaction.TransactionSource;
import teamwork.transaction.factory.LoadTransactionFactory;
import teamwork.transaction.factory.TransactionFactory;
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

    // 不是进度文件，则返回false
    if (!parser.loadFrom(file, "Transactions") || !parser.hasNext()) {
      return false;
    }

    // 无法解析进度文件中的Transaction节点或无Transaction节点
    if (!parser.loadFrom(file, "Transaction") || !parser.hasNext()) {
      return false;
    }

    TransactionSource source = new TransactionSource();
    TransactionFactory factory = new LoadTransactionFactory();

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
