package teamwork.model.controler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import teamwork.transaction.TransactionSource;
import teamwork.transaction.factory.LoadTransactionFactory;

public class LoadControler {
  public boolean load(String fileName) {
    return load(new File(fileName));
  }

  public boolean load(File file) {
    try {
      // 若文件不存在，则返回false
      if (!file.exists()) return false;

      BufferedReader bufIn = new BufferedReader(new FileReader(file));
      TransactionSource source = new TransactionSource();
      LoadTransactionFactory factory = new LoadTransactionFactory();

      String line;
      while ((line = bufIn.readLine()) != null) {
        source.add(factory.create(line));
      }

      source.run();
      bufIn.close();
    } catch (FileNotFoundException e) {
      return false;
    } catch (IOException e) {
      return false;
    }
    return true;
  }
}
