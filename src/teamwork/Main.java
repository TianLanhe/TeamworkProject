package teamwork;

import teamwork.model.ClassCatalog;
import teamwork.model.controler.TagsInitControler;
import teamwork.window.MainWindow;

public class Main {

  public static void main(String[] args) {
    new TagsInitControler(ClassCatalog.getInstance()).init();
    new MainWindow().run();
  }

}
