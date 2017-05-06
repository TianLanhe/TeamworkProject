package teamwork;

import teamwork.controler.TagsInitControler;
import teamwork.model.ClassCatalog;
import teamwork.window.MainWindow;

public class Main {

  public static void main(String[] args) {
    new TagsInitControler(ClassCatalog.getInstance()).init();
    new MainWindow().run();
  }

}
