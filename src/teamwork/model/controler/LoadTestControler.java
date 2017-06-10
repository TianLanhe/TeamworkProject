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
    XMLParser parser = new XMLParser();// XML������
    
    // ���ļ������ڣ��򷵻�false
    if (!file.exists()) return false;

    // ���ǲ����ļ����򷵻�false
    if (!parser.loadFrom(file, "TestTransactions") || !parser.hasNext()) {
      return false;
    }

    // �޷����������ļ��е�Transaction�ڵ�
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
