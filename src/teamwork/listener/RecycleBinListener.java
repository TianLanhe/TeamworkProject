package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import teamwork.window.RecycleBin;

public class RecycleBinListener implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent arg0) {
    new RecycleBin();
  }

}
