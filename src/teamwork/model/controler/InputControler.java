package teamwork.model.controler;

import java.io.File;

import teamwork.transaction.TransactionSource;
import teamwork.transaction.factory.TransactionFactory;
import teamwork.util.XMLParser;

public abstract class InputControler {

  protected XMLParser parser;// XML������
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
    // ���ļ������ڣ��򷵻�false
    if (!file.exists()) return false;

    // ���ǽ����ļ����򷵻�false
    if (!parser.loadFrom(file, rootName) || !parser.hasNext()) {
      return false;
    }

    // �޷����������ļ��е�Transaction�ڵ����Transaction�ڵ�
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
