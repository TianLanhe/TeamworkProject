package teamwork.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import teamwork.window.TestWindow;

public class TestListener implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
    new TestWindow().run();
  }

}
