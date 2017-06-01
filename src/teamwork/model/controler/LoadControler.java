package teamwork.model.controler;

import java.io.File;

import teamwork.transaction.TransactionSource;
import teamwork.transaction.factory.LoadTransactionFactory;
import teamwork.util.XMLParser;

public class LoadControler {
  public boolean load(String fileName) {
    return load(new File(fileName));
  }

  public boolean load(File file) {
    // 若文件不存在，则返回false
    if (!file.exists()) return false;

    XMLParser parser = new XMLParser();
    if (!parser.loadFrom(file, "Transaction")){
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
}
