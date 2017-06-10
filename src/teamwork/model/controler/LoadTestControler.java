package teamwork.model.controler;

import java.io.File;

import teamwork.transaction.TransactionSource;
import teamwork.transaction.factory.LoadTestTransactionFactory;
import teamwork.transaction.factory.TransactionFactory;
import teamwork.util.XMLParser;

public class LoadTestControler {

  public boolean load(String fileName) {
    return load(new File(fileName));
  }

  public boolean load(File file) {
    XMLParser parser = new XMLParser();// XML解析器
    
    // 若文件不存在，则返回false
    if (!file.exists()) return false;

    // 不是测试文件，则返回false
    if (!parser.loadFrom(file, "TestTransactions") || !parser.hasNext()) {
      return false;
    }

    // 无法解析进度文件中的Transaction节点
    if (!parser.loadFrom(file, "Transaction") || !parser.hasNext()) {
      return false;
    }

    TransactionSource source = new TransactionSource();
    TransactionFactory factory = new LoadTestTransactionFactory();

    while (parser.hasNext()) {
      source.add(factory.create(parser.next()));
    }

    source.run();
    return true;
  }

}
