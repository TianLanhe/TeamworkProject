package teamwork.model.controler;

import java.io.File;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import teamwork.transaction.factory.TransactionFactory;
import teamwork.transaction.factory.LoadStatisticsTransactionFactory;

public class ImportControler extends InputControler {

  public String loadPassword(String fileName) {
    return loadPassword(new File(fileName));
  }

  public String loadPassword(File file) {
    // 若文件不存在，则返回null
    if (!file.exists()) return "";

    // 解析失败，无Password标签，返回null
    if (!parser.loadFrom(file, "Password") || !parser.hasNext()) {
      return "";
    }

    Node node = parser.next();
    NamedNodeMap attrs = node.getAttributes();
    String pwd = attrs.getNamedItem("password").getNodeValue();
    return pwd;
  }

  @Override
  protected String getRootName() {
    return "StatisticsTransactions";
  }

  @Override
  protected TransactionFactory getTransactionFactory() {
    return new LoadStatisticsTransactionFactory();
  }
}
