package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import teamwork.window.RecycleBinWindow;

public class OpenRecycleBinListener implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent arg0) {
    new RecycleBinWindow().run();
  }

}
