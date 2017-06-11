package teamwork.model.controler;

import java.io.File;

import teamwork.transaction.TransactionSource;
import teamwork.transaction.factory.TransactionFactory;
import teamwork.util.XMLParser;

public abstract class InputControler {

  protected XMLParser parser;// XML解析器
  protected TransactionSource source;
  private String rootName;
  private TransactionFactory factory;

  public InputControler() {
    parser = new XMLParser();
    source = new TransactionSource();
    rootName = getRootName();
    factory = getTransactionFactory();
  }

  protected abstract String getRootName();

  protected abstract TransactionFactory getTransactionFactory();

  public boolean load(String fileName) {
    return load(new File(fileName));
  }

  public boolean load(File file) {
    // 若文件不存在，则返回false
    if (!file.exists()) return false;

    // 不是进度文件，则返回false
    if (!parser.loadFrom(file, rootName) || !parser.hasNext()) {
      return false;
    }

    // 无法解析进度文件中的Transaction节点或无Transaction节点
    if (!parser.loadFrom(file, "Transaction") || !parser.hasNext()) {
      return false;
    }

    while (parser.hasNext()) {
      source.add(factory.create(parser.next()));
    }

    source.run();
    return true;
  }
}
